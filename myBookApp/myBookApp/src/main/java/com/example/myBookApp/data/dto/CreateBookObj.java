package com.example.myBookApp.data.dto;

import com.example.myBookApp.data.model.book.books.Book;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBookObj {
    private Book b;

    public BookDto getBookDtoObj(){
        BookDto bookDto = new BookDto();
        bookDto.setId(b.getId());
        bookDto.setSlug(b.getSlug());
        bookDto.setImage(b.getImage());
        bookDto.setAuthors(b.getAuthor().toString());
        bookDto.setTitle(b.getTitle());
        bookDto.setBestseller(b.getIsBestseller() == 1);

        bookDto.setRating(0);//NULL
        bookDto.setStatus("NULL"); // NULL

        bookDto.setPrice(b.getPriceOld());
        bookDto.setDiscount(b.getDiscount());
        bookDto.setDiscountPrice(b.getDiscount() > 0 ? (int)((1 + (b.getDiscount()* 0.01)) * b.getPriceOld()) : null);
        return bookDto;
    }
}
