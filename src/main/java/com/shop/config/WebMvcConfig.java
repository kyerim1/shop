package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    // 실제 업로드 경로와  웹에 서 사용할 주소 를 연결 ,  실제 업로드경로는 웹에서
    // 사용할수 없는 경로 이기때문에 웹용으로 /images/ 를 쓰겠다 라고 등록
    //  웹에서 /images/12.jpg 라는 이미지 요청이 들어오면
    //  실제 경로 c:/shop/12.jpg의 이미지를 제공한다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}
