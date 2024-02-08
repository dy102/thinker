package com.example.thinker.service;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;


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

    @Override
    public Image getImageByMember(Member member) {
        return member.getImage();
    }

    public void makeBasicImage(Member member, String filePath) throws IOException {
        Image image = imageRepository.findByData(Files.readAllBytes(Paths.get(filePath)));
        if (image == null) {
            saveImage(filePath);
            image = imageRepository.findByData(Files.readAllBytes(Paths.get(filePath)));
        }
        member.setImage(image);
        memberRepository.save(member);
    }

    public void saveImage(String filePath) throws IOException {
        // 파일을 읽어와서 데이터베이스에 저장
        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);

        Image image = new Image();
        image.setData(data);
        image.setFileName(path.getFileName().toString());

        imageRepository.save(image);
    }
}
