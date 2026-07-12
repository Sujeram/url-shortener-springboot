package com.example.urlshortener.service;

import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }
    public UrlResponse shortenUrl(UrlRequest request) {

    return new UrlResponse();

}

}