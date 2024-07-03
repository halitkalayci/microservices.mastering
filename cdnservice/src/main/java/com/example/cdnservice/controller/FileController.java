package com.example.cdnservice.controller;

import com.example.cdnservice.service.CdnService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileController {

    private final CdnService cdnService;
    private final MinioClient minioClient;
    public FileController(CdnService cdnService, MinioClient minioClient) {
        this.cdnService = cdnService;
        this.minioClient = minioClient;
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

    @PostMapping("/zipFiles")
    public void zipFiles(@RequestBody List<String> fileNames, HttpServletResponse response) throws Exception {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"files.zip\"");
        response.setContentType("application/zip");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for (String fileName : fileNames) {
                try (InputStream inputStream = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket("default")
                                .object(fileName)
                                .build())) {

                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);
                    }

                    zipOutputStream.closeEntry();
                } catch (MinioException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }
            }
        }
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
