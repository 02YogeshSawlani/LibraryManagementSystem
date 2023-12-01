package com.example.Library.Management.System.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    private Integer authorId;

    @Column(nullable = false)
    private String authorName;

    private int age;

    private double rating;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book>bookList= new ArrayList<>();


}
