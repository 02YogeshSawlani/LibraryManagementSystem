package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enums.Genre;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book,Integer authorId) throws Exception{
        // what are the steps of code that we should write here??
        //Final goal is to save entity
        //Author obj is missing
        Optional<Author>optionalAuthor= authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Id entered is invalid");

        }
        Author author=optionalAuthor.get();

        book.setAuthor(author);

        //  Becuase it is bidirectional mapping
        // Author should also have info of book entity

        author.getBookList().add(book);
        // Now both entities are modified
        // we will save only author because of cascading, book will automsatically saved

        authorRepository.save(author);

        return "Book has been added in DB";
    }

    public List<String> getBooksByGenre(Genre genre){

        List<Book> bookList= bookRepository.findBooksByGenre(genre);
        List<String> bookNames= new ArrayList<>();

        for(Book book:bookList){
            bookNames.add(book.getBookName());
        }
        return bookNames;

    }

}
