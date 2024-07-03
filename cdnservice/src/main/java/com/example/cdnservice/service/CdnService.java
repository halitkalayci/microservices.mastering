package com.example.cdnservice.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CdnService {
    private final MinioClient minioClient;

    public CdnService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String upload(MultipartFile file) throws Exception {
        InputStream stream = file.getInputStream();
        String fileName =  LocalDateTime.now().toString() + file.getOriginalFilename();
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket("default1").build()))
        {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("default1").build());
        }
        ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder()
                        .bucket("default1")
                        .object(fileName)
                        .stream(stream, file.getSize(), -1)
                        .contentType(file.getContentType()).build());
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        .bucket("default1")
                        .object(fileName)
                        .method(Method.GET)
                .build());
    }
}
