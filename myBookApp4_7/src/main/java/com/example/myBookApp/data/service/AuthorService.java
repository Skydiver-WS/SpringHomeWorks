package com.example.myBookApp.data.service;

import com.example.myBookApp.data.dto.Author;
import com.example.myBookApp.data.dto.Book;
import com.example.myBookApp.data.dto.GroupAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, List<Author>> getAuthorInfo(){
//        List<Author> list = jdbcTemplate.query("SELECT * FROM authors a JOIN alphabetRus al ON " +
//                "a.indexABC = al.id", (ResultSet rs, int rowNum) -> {
//            Author author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setAuthor(rs.getString("author"));
//            author.setIndex(rs.getString("let"));
//            return author;
//        });
//
//        List<GroupAuthor> listGroupAuthor = jdbcTemplate.query("SELECT * FROM alphabetRus", (ResultSet rs, int rowNum) -> {
//            GroupAuthor groupAuthor = new GroupAuthor();
//            String index = rs.getString("let");
//            groupAuthor.setIndex(index);
//            groupAuthor.setListAuthor(list.stream().filter(a -> a.getIndex().equals(index)).toList());
//            return groupAuthor;
//        }).stream().filter(a -> !a.getListAuthor().isEmpty()).toList();

        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("first_name") );
            author.setLastName(rs.getString("last_name"));
            return author;
        });

        return authors.stream().collect(Collectors.groupingBy((Author a) -> {return a.getLastName().substring(0,1);}));
    }
}
