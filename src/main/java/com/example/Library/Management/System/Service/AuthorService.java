package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){

        authorRepository.save(author);
        return "Author has been added to the db";

    }

    public List<String>getAllAuthorNames(){

        List<Author>authors = authorRepository.findAll();
        List<String>authorNames=new ArrayList<>();

        for(Author author:authors){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public Author getAuthorById(Integer id) throws Exception{
        Optional<Author>optionalAuthor= authorRepository.findById(id);

        // when we are not sure that whether we will get entity or null at that time we can use Optional datatype which
        //help us to prevent null pointer exception

        if(!optionalAuthor.isPresent()){
            // Throw an error
            throw new Exception("Id entered is invalid");
        }
        Author author=optionalAuthor.get();
        return author;

    }



}
