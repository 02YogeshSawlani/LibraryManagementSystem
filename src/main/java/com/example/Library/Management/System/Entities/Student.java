package com.example.Library.Management.System.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  // This annotation means It is structure that will be reflected in db
@Table(name = "student")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private Integer studentId;  // This will behave as primary key

    private String name;

    private int age;

    private String emailId;

    @Column(name="contactNo",unique = true,nullable = false)
    private String mobileNo;
}
