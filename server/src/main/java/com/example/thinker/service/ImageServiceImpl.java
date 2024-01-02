package com.example.thinker.service;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void uploadImage(String fileName, MultipartFile file, Member loginMember) throws IOException {
        Image image = loginMember.getImage();
        if (image == null) {
            image = new Image();
        }
        image.setFileName(fileName);
        image.setData(file.getBytes());
        imageRepository.save(image);

        loginMember.setImage(image);
        memberRepository.save(loginMember);
    }

    @Override
    public void uploadImage(String fileName, MultipartFile file, Thinking thinking) throws IOException {

    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    // 이미지 조회 메서드
    @Override
    public Image getImageByMember(Member member) {
        return member.getImage();
    }

    public void makeBasicImage(Member member) {
        Optional<Image> image = imageRepository.findById(1L);
        if (image.isPresent()) {
            member.setImage(image.get());
            memberRepository.save(member);
        }
    }
}
