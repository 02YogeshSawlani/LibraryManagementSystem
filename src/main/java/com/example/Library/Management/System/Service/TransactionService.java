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

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    private static final Integer MAX_LIMIT_OF_BOOK=3;



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



        //setting FK

        //saving relevant Entities
    }

}
