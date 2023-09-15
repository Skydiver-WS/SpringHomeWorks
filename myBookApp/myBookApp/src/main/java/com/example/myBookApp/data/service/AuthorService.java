package com.example.myBookApp.data.service;

import com.example.myBookApp.data.model.authors.Author;
import com.example.myBookApp.data.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Map<String, List<Author>> getAuthorInfo() {
        List<Author> authors = authorsRepository.findAll();
        return authors.stream().collect(Collectors.groupingBy((Author a) -> a.getLastName().substring(0, 1)));
    }
}
