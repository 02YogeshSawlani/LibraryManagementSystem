package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libraryCard")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LibraryCard {
    @Id
    private Integer cardNo;

    @Enumerated(value = EnumType.STRING)  // sql only knows primitive datatypes this annotation helps us when userdefine datatypes are there
    private CardStatus cardStatus;
    /*
       Library  card need to be connect with student table

     */
    @OneToOne
    @JoinColumn
    private Student student;
}
