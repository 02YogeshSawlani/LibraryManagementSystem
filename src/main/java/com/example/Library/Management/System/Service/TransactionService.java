package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Exceptions.*;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    private static final Integer MAX_LIMIT_OF_BOOK=3;

    private static final Integer FINE_PER_DAY=5;

    public String issueBook(Integer bookId,Integer cardId) throws Exception{

        Transaction transaction= new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        // validation
           // 1. valid bookId
        Optional<Book>bookOptional=bookRepository.findById(bookId);

        if(!bookOptional.isPresent()){
            throw new BookNotFound("BookId is Invalid");
        }

           //2. Availability of book
        Book book =bookOptional.get();
        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book is unavailable at this moment");
        }

           //3. valid cardId
        Optional<LibraryCard> libraryCardOptional=cardRepository.findById(cardId);
        if(!libraryCardOptional.isPresent()){
            throw new CardNotFound("CardId is invalid");
        }
        LibraryCard libraryCard=libraryCardOptional.get();

           //4. valid card status

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvaliCardStatusException("Card status is Not active");
        }


          //5. Maximum no of book issue: maxLimit ==3
         if(libraryCard.getNoOfBookIssued()==MAX_LIMIT_OF_BOOK){
             throw new MaxBookLimitExceededException("Maximum Book Limit is"+MAX_LIMIT_OF_BOOK+"You can't issue more book than this");
         }

        /* If I pass all of above criteria ie validation then it mean now I can create success case that is transaction can be initialize now */

        //creating the transaction Entity
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()+1);

        book.setAvailable(false);
        //setting FK
       transaction.setBook(book);
       transaction.setCard(libraryCard);

        //saving relevant Entities
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        //save parent entities
       transactionRepository.save(transaction);
        return "The book with bookId "+bookId+" has been issued" + "to cars with "+cardId;
    }

    public String returnBook(Integer bookId,Integer cardId){
        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

        //I need to find out that issued transaction
        Transaction transaction=transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);
        Date issueDate= transaction.getCreatedOn();

        //predefied method to use calculate days
        long millSeconds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days= TimeUnit.DAYS.convert(millSeconds,TimeUnit.MILLISECONDS);
        int fineAmount=0;

        if(days>15){
            fineAmount= Math.toIntExact((days - 15) * FINE_PER_DAY);
        }
        Transaction newTransaction=new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);

        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //setting FK's
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBookIssued(card.getNoOfBookIssued()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return  "Book has been returned";
        }

}
