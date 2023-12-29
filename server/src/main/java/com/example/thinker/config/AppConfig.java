package com.example.thinker.config;

import com.example.thinker.service.MemberService;
import com.example.thinker.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }
}
