package com.example.urlshortener.service;

import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.model.UrlMapping;

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

    return new UrlResponse();

    }

}