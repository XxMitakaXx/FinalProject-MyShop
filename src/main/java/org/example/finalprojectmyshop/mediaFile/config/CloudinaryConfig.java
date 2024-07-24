package org.example.finalprojectmyshop.mediaFile.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_URL = "cloudinary://%s:%s@dqqcptgsa";
    private final String API_KEY = "436236498958533";
    private final String API_SECRET = "jdsfbQ5oAKEWYEAJ9OTUC0NEamo";


    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(String.format(CLOUD_URL, API_KEY, API_SECRET));
    }

}
