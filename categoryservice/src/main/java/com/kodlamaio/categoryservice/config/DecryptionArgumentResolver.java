package com.kodlamaio.categoryservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.categoryservice.annotation.Decrypted;
import com.kodlamaio.categoryservice.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class DecryptionArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;
    private final EncryptionService encryptionService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Decrypted.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String encryptedData = webRequest.getParameter(parameter.getParameterName());
        if(encryptedData == null)
            throw new HttpMessageNotReadableException("No encrypted data.");
        String decryptedData = encryptionService.decrypt(encryptedData);
        Type targetType = parameter.getGenericParameterType();

        return objectMapper.readValue(decryptedData, objectMapper.constructType(targetType));
    }
}
