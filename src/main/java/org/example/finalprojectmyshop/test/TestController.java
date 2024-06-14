package org.example.finalprojectmyshop.test;

import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class TestController {
    private final MediaFileService mediaFileService;

    public TestController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @GetMapping("/")
    public String index() {
        File file = new File("C:\\Users\\mitak\\Desktop\\FinalProject-MyShop\\src\\main\\resources\\2020-porsche-911-turbo.jpg");
        this.mediaFileService.upload(file);

        return "home";
    }
}
