package com.example.cdnservice.controller;

import com.example.cdnservice.service.CdnService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileController {

    private final CdnService cdnService;

    public FileController(CdnService cdnService) {
        this.cdnService = cdnService;
    }

    @PostMapping("upload")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) throws Exception{
        return ResponseEntity.ok(cdnService.upload(file));
    }

    @PostMapping("upload2")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files,HttpServletResponse response) throws Exception{
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        for(int i=0; i<files.length; i++)
        {
            String url = cdnService.upload(files[i]);
            response.getWriter().write((i+1)+". dosya yüklendi, url: " + url + "\n");
            response.getWriter().flush();
            Thread.sleep(1000);
        }
        response.getWriter().write("data: İşlem tamamlandı\n\n");
        response.getWriter().flush();
        response.getWriter().close();


        return ResponseEntity.ok("");
    }

    @GetMapping("progress")
    public ResponseEntity<String> getProgress(HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        for (int i = 0; i <= 10; i++) {
            int progress = i * 10;
            response.getWriter().write("data: " + progress + "%\n\n");
            response.getWriter().flush();
            Thread.sleep(1000); // 1 saniye bekle
        }
        response.setContentLength(-1);
        response.setContentType("application/json");
        response.getWriter().write("abc");
        return ResponseEntity.ok("Merhaba");
    }
}
