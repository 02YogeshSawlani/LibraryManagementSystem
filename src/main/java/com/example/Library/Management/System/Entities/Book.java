package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table  // If you not write table name then it will consider class name as table name
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Integer bookId;

    private String bookName;

    private int price;

    private int noOfPages;

    private Genre genre;

    private double rating;

    @ManyToOne
    @JoinColumn
    private Author author;


}
