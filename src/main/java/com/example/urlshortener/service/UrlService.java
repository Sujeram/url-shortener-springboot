package com.example.urlshortener.service;

import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.model.UrlMapping;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Optional;
@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }
    public UrlResponse shortenUrl(UrlRequest request) {

    Optional<UrlMapping> existing =
            repository.findByOriginalUrl(request.getUrl());

    if (existing.isPresent()) {
        return new UrlResponse(
                "http://localhost:8080/" +
                        existing.get().getShortCode());
    }

    String shortCode = generateShortCode(request.getUrl());

    UrlMapping mapping = new UrlMapping();

    mapping.setOriginalUrl(request.getUrl());
    mapping.setShortCode(shortCode);

    repository.save(mapping);

    return new UrlResponse(
            "http://localhost:8080/" + shortCode);

    }
    public String getOriginalUrl(String shortCode) {

    UrlMapping mapping = repository.findByShortCode(shortCode)
            .orElseThrow(() -> new RuntimeException("Short URL not found"));

    return mapping.getOriginalUrl();

    }
    private String generateShortCode(String url) {

    try {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.substring(0, 6);

    } catch (NoSuchAlgorithmException e) {

        throw new RuntimeException(e);

    }

}

}