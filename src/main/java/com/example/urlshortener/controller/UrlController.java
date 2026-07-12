package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }
    @PostMapping("/shorten")
    public UrlResponse shortenUrl(@RequestBody UrlRequest request) {

        return service.shortenUrl(request);

    }
    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode,
                     HttpServletResponse response) throws IOException {

    String originalUrl = service.getOriginalUrl(shortCode);

    response.sendRedirect(originalUrl);

    }

}