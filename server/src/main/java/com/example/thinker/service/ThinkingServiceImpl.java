package com.example.thinker.service;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.Reply;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDtos;
import com.example.thinker.dto.request.ThinkingRequest;
import com.example.thinker.dto.response.PremiumThinkingResponse;
import com.example.thinker.dto.response.ThinkingDetailResponse;
import com.example.thinker.dto.response.ThinkingResponse;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.ReplyRepository;
import com.example.thinker.repository.ThinkingRepository;
import com.example.thinker.util.ScrollPaginationCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThinkingServiceImpl implements ThinkingService {
    private static final String THINKING_NULL = "존재하지 않는 게시물입니다.";
    private final MemberRepository memberRepository;
    private final ThinkingRepository thinkingRepository;
    private final ReplyRepository replyRepository;
    private final ImageRepository imageRepository;

    @Override
    public PremiumThinkingResponse findPremiumThinking(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Thinking> thinkings = thinkingRepository.findByIsPremiumIsTrueOrderByIdDesc(pageRequest);
        return PremiumThinkingResponse.form(thinkings);
    }

    @Override
    public ThinkingResponse findThinking(String kind, int size, Long lastId) {//무한 스크롤
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Thinking> thinkings;
        if (kind.equals("recent")) {
            Page<Thinking> page = thinkingRepository.findAllByIdLessThanOrderByIdDesc(lastId, pageRequest);
            thinkings = page.getContent();
        } else if (kind.equals("like")) {
            Page<Thinking> page =
                    thinkingRepository.findAllByLikeCountIsLessThanEqualOrderByLikeCountDesc(lastId, pageRequest);
            thinkings = page.getContent();
        } else if (kind.equals("view")) {
            Page<Thinking> page =
                    thinkingRepository.findAllByViewCountLessThanEqualOrderByViewCountDesc(lastId, pageRequest);
            thinkings = page.getContent();
        } else {
            throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
        }
        ScrollPaginationCollection<Thinking> cursor = new ScrollPaginationCollection<>(thinkings, size);
        return ThinkingResponse.of(cursor, thinkingRepository.countThinkingByIdIsNotNull());
    }

    @Override
    public Thinking makeThinking(ThinkingRequest thinkingRequest, Member loginMember) throws IOException {
        Thinking thinking = new Thinking();
        makeThinkingByThinkingRequest(thinkingRequest, thinking);

        thinking.setWriter(loginMember);
        thinking.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
        thinking.setLikeCount(0L);
        thinking.setRepliesCount(0L);
        thinking.setViewCount(0L);
        thinkingRepository.save(thinking);
        return thinking;
    }

    @Override
    public Thinking updateThinking(Long thinkingId, ThinkingRequest thinkingRequest, Member loginMember) throws IOException {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            if (thinking.get().getWriter().equals(loginMember)) {
                makeThinkingByThinkingRequest(thinkingRequest, thinking.get());
                thinkingRepository.save(thinking.get());
            }
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        throw new IllegalArgumentException(THINKING_NULL);
    }

    @Override
    public ThinkingDetailResponse getThinkingDetail(Long thinkingId, Member loginMember) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            return ThinkingDetailResponse.form(thinking.get(), loginMember);
        }
        throw new IllegalArgumentException(THINKING_NULL);
    }

    @Override
    public void deleteThinking(Member loginMember, Long thinkingId) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            if (thinking.get().getWriter().equals(loginMember)) {
                thinkingRepository.delete(thinking.get());
                return;
            }
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        throw new IllegalArgumentException(THINKING_NULL);
    }

    @Override
    public ThinkingResponse findThinkingByTitle(String title, String kind, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        Page<Thinking> thinkingPage;
        List<Thinking> thinkings = new ArrayList<>();
        if (kind.equals("recent")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByTitleContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(title, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("like")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByTitleContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(title, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("view")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByTitleContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(title, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        }
        ScrollPaginationCollection<Thinking> cursor = new ScrollPaginationCollection<>(thinkings, size);
        return ThinkingResponse.of(cursor, thinkingRepository.countThinkingByIdIsNotNull());
    }

    @Override
    public ThinkingResponse findThinkingByContent(String content, String kind, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        Page<Thinking> thinkingPage;
        List<Thinking> thinkings = new ArrayList<>();
        if (kind.equals("recent")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByContentsContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(content, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("like")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByContentsContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(content, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("view")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByContentsContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(content, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        }
        ScrollPaginationCollection<Thinking> cursor = new ScrollPaginationCollection<>(thinkings, size);
        return ThinkingResponse.of(cursor, thinkingRepository.countThinkingByIdIsNotNull());
    }

    @Override
    public ThinkingResponse findThinkingByWriter(String writer, String kind, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        Page<Thinking> thinkingPage;
        List<Thinking> thinkings = new ArrayList<>();
        if (kind.equals("recent")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByWriterContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(writer, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("like")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByWriterContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(writer, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("view")) {
            thinkingPage =
                    thinkingRepository
                            .searchAllByWriterContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(writer, lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        }
        ScrollPaginationCollection<Thinking> cursor = new ScrollPaginationCollection<>(thinkings, size);
        return ThinkingResponse.of(cursor, thinkingRepository.countThinkingByIdIsNotNull());
    }

    @Override
    public ThinkingResponse getMemberActivityDetails(Long memberId, int size, Long lastId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        Optional<Member> member = memberRepository.findById(memberId);
        List<Thinking> thinkings;
        if (member.isPresent()) {
            Page<Thinking> thinkingPage =
                    thinkingRepository
                            .findAllByWriterAndIdLessThanEqualOrderByIdDesc(member.get(), lastId, pageRequest);
            thinkings = thinkingPage.getContent();
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        ScrollPaginationCollection<Thinking> cursor = new ScrollPaginationCollection<>(thinkings, size);
        return ThinkingResponse.of(cursor, thinkingRepository.countThinkingByIdIsNotNull());
    }

    @Override
    public ThinkingDtos getMyThinkings(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Thinking> thinkingPage;
        List<Thinking> thinkings = new ArrayList<>();
        if (kind.equals("recent")) {
            thinkingPage = thinkingRepository.findAllByWriterOrderByIdDesc(loginMember, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("like")) {
            thinkingPage = thinkingRepository.findAllByWriterOrderByLikeCountDesc(loginMember, pageRequest);
            thinkings = thinkingPage.getContent();
        } else if (kind.equals("view")) {
            thinkingPage = thinkingRepository.findAllByWriterOrderByViewCountDesc(loginMember, pageRequest);
            thinkings = thinkingPage.getContent();
        }
        return ThinkingDtos.form(thinkings);
    }

    @Override
    public ThinkingDtos getThinkingsByMyReply(Member loginMember, String kind, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        List<Thinking> thinkings = new ArrayList<>();
        List<Reply> replies = replyRepository.findAllByMember(loginMember);
        for (Reply reply : replies) {
            thinkings.add(reply.getThinking());
        }
        if (kind.equals("recent")) {
            thinkings = thinkings.stream()
                    .sorted(Comparator.comparingLong(Thinking::getId).reversed())
                    .toList();
        } else if (kind.equals("like")) {
            thinkings = thinkings.stream()
                    .sorted(Comparator.comparingLong(Thinking::getLikeCount).reversed())
                    .toList();
        } else if (kind.equals("view")) {
            thinkings = thinkings.stream()
                    .sorted(Comparator.comparingLong(Thinking::getViewCount).reversed())
                    .toList();
        }
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), thinkings.size());
        Page<Thinking> thinkingPage = new PageImpl<>(thinkings.subList(start, end), pageRequest, thinkings.size());
        thinkings = thinkingPage.getContent();
        return ThinkingDtos.form(thinkings);
    }

    private void makeThinkingByThinkingRequest(ThinkingRequest thinkingRequest, Thinking thinking) throws IOException {
        List<Image> thinkingImages = new ArrayList<>();
        if (thinkingRequest.thinkingImage() != null) {
            for (MultipartFile multipartFile : thinkingRequest.thinkingImage()) {
                if (multipartFile == null) {
                    continue;
                }
                Image image = imageRepository.findByData(multipartFile.getBytes());
                if (image == null) {
                    image = new Image();
                    image.setData(multipartFile.getBytes());
                    image.setFileName(getFileName(thinking, image));
                    imageRepository.save(image);
                }
                thinkingImages.add(image);
            }
        }
        if (!thinkingImages.isEmpty()) {
            thinking.setImage(thinkingImages);
        }
        thinking.setTitle(thinkingRequest.thinkingTitle());
        thinking.setContents(thinkingRequest.thinkingContents());
        thinking.setIsPremium(thinkingRequest.isPremium());
    }

    private static String getFileName(Thinking thinking, Image image) {
        return "thinking" + "[" + thinking.getId() + "]" + "[" + image.getId() + "]";
    }
}
