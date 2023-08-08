package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    //private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        //book.setId(context.getBean(IdProvider.class).provideId(book));
        //repo.add(book);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());
        jdbcTemplate.update("INSERT INTO books(author,title,size) VALUES(:author, :title, :size)", parameterSource);
        logger.info("store new book: " + book);
    }
    //        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("id", bookIdToRemove);
//        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
//        logger.info("remove book completed: ");
//        return true;
    @Override
    public void removeItemById(Object query) {
        String q = ((String) query).trim();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(q);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", q);
        parameterSource.addValue("author", q);
        parameterSource.addValue("title", q);
        if (matcher.matches()){
            jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
        }
        else {
            String sql = "DELETE FROM books WHERE author = :author OR title = :title";
            jdbcTemplate.update(sql, parameterSource);
        }
        logger.info("remove book completed: ");
    }

    @Override
    public Boolean findElement(Object obj) {
       String q = ((String) obj).trim();
       Pattern pattern = Pattern.compile("\\d+");
       Matcher matcher = pattern.matcher(q);
       String sql = "";
       if(matcher.matches()){
           sql = "SELECT * FROM books WHERE id = " + Integer.valueOf(q);
       }else {
           sql = "SELECT * FROM books WHERE author = '" + q + "' OR title = '" + q + "'";
       }
       return jdbcTemplate.query(sql, ResultSet::next);
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }


}

