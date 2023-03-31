package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vn.petstore.website.model.Transaction;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
