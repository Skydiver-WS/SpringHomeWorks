package com.example.myBookApp.data.service.books;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.BooksPageDto;
import com.example.myBookApp.data.dto.CreateBookObj;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.model.book.books.Tags;
import com.example.myBookApp.data.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTagPageService {
    private final TagsRepository tagsRepository;

    @Autowired
    public BookTagPageService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<Tags> getListTags(){
        return tagsRepository.findAll();
    }
    public List<BookDto> getBooksByTags(Integer offset, Integer limit, String tag){
        Pageable pageRequest = PageRequest.of(offset, limit);
        Tags tagsList= tagsRepository.getByTag(tag);
        List<Book> bookList = new ArrayList<>(tagsList.getListBooks().stream().toList());
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), bookList.size());
        List<Book> pageContent = bookList.subList(start, end);
        Page <Book> page = new PageImpl<>(pageContent, pageRequest, bookList.size());
        return page.getContent().stream().map(book -> new CreateBookObj(book).getBookDtoObj()).toList();
    }
}
