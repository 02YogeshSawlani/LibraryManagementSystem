package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraryCard")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // autogenerate cardNo value for me
    private Integer cardNo; //this is act as a PK in library card table

    @Enumerated(value = EnumType.STRING)  // sql only knows primitive datatypes this annotation helps us when userdefine datatypes are there
    private CardStatus cardStatus;

    private Integer noOfBookIssued;

    private String nameOnCard;
    /*
       Library  card need to be connect with student table

     */
    @OneToOne
    @JoinColumn
    private Student student; // this is act as a foreign key of library card table
    // this variable has to be put in mapped attribute in the parent class

      // connect with transaction
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction>transactionList =new ArrayList<>();

}
