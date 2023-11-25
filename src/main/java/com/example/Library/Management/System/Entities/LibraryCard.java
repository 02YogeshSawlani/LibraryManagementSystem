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

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
}
