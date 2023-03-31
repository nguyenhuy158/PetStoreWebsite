package vn.petstore.website.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.petstore.website.model.Transaction;
import vn.petstore.website.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void checkout(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
