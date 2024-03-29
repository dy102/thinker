package com.example.thinker.service;

import com.example.thinker.domain.Grade;
import com.example.thinker.domain.Member;
import com.example.thinker.dto.MemberDataDto;
import com.example.thinker.dto.MemberSimpleDto;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.thinker.constants.ErrorConst.NO_MEMBER;
import static com.example.thinker.domain.Grade.ADVANCED;
import static com.example.thinker.domain.Grade.BEGINNER;
import static com.example.thinker.domain.Grade.EXPERT;
import static com.example.thinker.domain.Grade.INTERMEDIATE;
import static com.example.thinker.domain.Grade.MANAGER;
import static com.example.thinker.domain.Grade.MASTER;
import static com.example.thinker.domain.Grade.THINKER;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member create(MemberDataRequest memberDataRequest) {
        validateMemberDataRequest(memberDataRequest);

        Member member = new Member();
        makeMember(memberDataRequest, member);
        member.setPoint(0L);
        member.setAccumulatedPoint(0L);
        member.setGrade(Grade.BEGINNER.getName());
        memberRepository.save(member);

        return member;
    }

    private void validateCustomId(String customId) {
        if (customId.length() < 2 || customId.length() > 20) {
            throw new IllegalArgumentException("아이디는 2~20자여야 합니다.");
        }

        String specialCharacterPattern = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(specialCharacterPattern);
        Matcher matcher = pattern.matcher(customId);
        if (matcher.find()) {
            throw new IllegalArgumentException("아이디에는 영어와 숫자만 입력 가능합니다.");
        }

        if (memberRepository.findByCustomId(customId) != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    private void validatePw(String pw) {
        if (pw.length() < 6 || pw.length() > 20) {
            throw new IllegalArgumentException("비밀번호는 6~20자여야 합니다.");
        }

        String allowedSpecialCharacters = "!@&^";
        String pattern = "[" + Pattern.quote(allowedSpecialCharacters) + "]";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(pw);
        if (matcher.find()) {
            throw new IllegalArgumentException("비밀번호에는 특수문자 !,@,&,^만 들어갈 수 있습니다.");
        }
    }

    private void validateName(String name) {
        if (name.length() < 2 || name.length() > 10) {
            throw new IllegalArgumentException("이름은 2~10자여야 합니다.");
        }

        String specialCharacterPattern = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(specialCharacterPattern);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            throw new IllegalArgumentException("이름에는 특수문자가 들어갈 수 없습니다.");
        }

        if (memberRepository.findByName(name) != null) {
            throw new IllegalArgumentException("");
        }
    }

    private LocalDate validateAndGetBirthday(MemberDataRequest memberDataRequest) {
        try {
            return Convertor.stringToLocalDate(
                    memberDataRequest.year(),
                    memberDataRequest.month(),
                    memberDataRequest.day());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("");
        }
    }

    private void validateGender(String gender) {
        if (!gender.equals("woman") && !gender.equals("man")) {
            throw new IllegalArgumentException("성별을 선택해주세요.");
        }
    }

    private void makeMember(MemberDataRequest memberDataRequest, Member member) {
        member.setCustomId(memberDataRequest.customId());
        member.setPw(memberDataRequest.pw());
        member.setName(memberDataRequest.nickname());
        member.setBirthday(validateAndGetBirthday(memberDataRequest));
        member.setGender(memberDataRequest.gender());
    }

    @Override
    public MemberDataDto read(Member loginMember) {
        return MemberDataDto.form(loginMember);
    }

    @Override
    public void setGradeByAccumulatedPoint(Member loginMember) {
        Long gradePoint = loginMember.getAccumulatedPoint();
        if (!loginMember.getGrade().equals(MANAGER.getName())) {
            if (checkGrade(BEGINNER, gradePoint)) {
                loginMember.setGrade(BEGINNER.getName());
            } else if (checkGrade(INTERMEDIATE, gradePoint)) {
                loginMember.setGrade(INTERMEDIATE.getName());
            } else if (checkGrade(ADVANCED, gradePoint)) {
                loginMember.setGrade(ADVANCED.getName());
            } else if (checkGrade(EXPERT, gradePoint)) {
                loginMember.setGrade(EXPERT.getName());
            } else if (checkGrade(MASTER, gradePoint)) {
                loginMember.setGrade(MASTER.getName());
            } else if (checkGrade(THINKER, gradePoint)) {
                loginMember.setGrade(THINKER.getName());
            }
            memberRepository.save(loginMember);
        }
    }

    private static boolean checkGrade(Grade grade, Long gradePoint) {
        return grade.getMinimumPoint() <= gradePoint && gradePoint <= grade.getMaximumPoint();
    }

    @Override
    public MemberSimpleDto readSimple(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new IllegalArgumentException(NO_MEMBER);
        }
        return MemberSimpleDto.form(member.get());
    }

    @Override
    public void update(Member loginMember, MemberDataRequest memberDataRequest) {
        validateMemberDataRequest(memberDataRequest);
        makeMember(memberDataRequest, loginMember);
        memberRepository.save(loginMember);
    }

    private void validateMemberDataRequest(MemberDataRequest memberDataRequest) {
        validateCustomId(memberDataRequest.customId());
        validatePw(memberDataRequest.pw());
        validateName(memberDataRequest.nickname());
        validateAndGetBirthday(memberDataRequest);
        validateGender(memberDataRequest.gender());
    }

    @Override
    public Member login(String customId, String pw) {
        Member member = memberRepository.findByCustomId(customId);
        if (member != null && (member.getPw().equals(pw))) {
            return member;
        }
        throw new IllegalArgumentException(NO_MEMBER);
    }
}
