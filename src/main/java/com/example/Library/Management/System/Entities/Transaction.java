package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private Date issuedDate;

    private Date returnDate;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn;  // handled by spring internally

    @UpdateTimestamp
    private Date lastModifiedOn;

    // Connect FK with book entity
    @ManyToOne
    @JoinColumn
    private Book book;

    // with card entity
    @ManyToOne
    @JoinColumn
    private LibraryCard card;
}
