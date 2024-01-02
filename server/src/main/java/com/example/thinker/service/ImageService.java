package com.example.thinker.service;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void uploadImage(String fileName, MultipartFile file, Member loginMember) throws IOException;

    void uploadImage(String fileName, MultipartFile file, Thinking thinking) throws IOException;

    public List<Image> getAllImages();

    public Image getImageByMember(Member member);

    public void makeBasicImage(Member member);
}

