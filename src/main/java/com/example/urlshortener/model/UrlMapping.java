package com.example.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "urlMappings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {

    @Id
    private String id;

    private String originalUrl;

    private String shortCode;

}