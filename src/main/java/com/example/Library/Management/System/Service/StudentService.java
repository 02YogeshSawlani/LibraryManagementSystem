package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JavaMailSender mailSender;
        public String addStudent(Student student){
              studentRepository.save(student);
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            String body="Hi "+student.getName()+" !"+" You have Successfully registered in Library Management Database, you can start using library features";
           mailMessage.setFrom("learningspringboot4@gmail.com");
           mailMessage.setTo(student.getEmailId());
           mailMessage.setSubject("Welcome LMS Services");
           mailMessage.setText(body);
           mailSender.send(mailMessage);
              return  "Student has been save to the DB";
        }

}
