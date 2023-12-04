package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public LibraryCard generateCard(){

        LibraryCard card =new LibraryCard();

        card.setCardStatus(CardStatus.NEW);

        card=cardRepository.save(card);

        return card;
    }

    public String associateStudentWithCard(Integer studentId, Integer cardNo){
        // I am having only PK of both


        // But I need the entities to set attribute and save

        Optional<Student>studentOptional = studentRepository.findById(studentId);
        Student student= studentOptional.get();

        Optional<LibraryCard>optionalLibraryCard =cardRepository.findById(cardNo);
        LibraryCard libraryCard= optionalLibraryCard.get();

        // Setting the attributes of library Object

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        // Setting the attributes of Student Object
        student.setLibraryCard(libraryCard);


        studentRepository.save(student);
        return "card with "+cardNo+" has been associated to Student with "+studentId;

    }
}
