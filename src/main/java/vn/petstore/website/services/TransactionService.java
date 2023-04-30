package vn.petstore.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.petstore.website.dto.PaginatedOrderResponse;
import vn.petstore.website.model.HistoryItemDto;
import vn.petstore.website.model.Transaction;
import vn.petstore.website.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    public void checkout(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<HistoryItemDto> getHistoryItemDtos() {

        List<Transaction> transactions = transactionRepository.findByUser(userService.getCurrentUser());
        List<HistoryItemDto> historyItemDtos = transactions.stream().map(arg0 -> new HistoryItemDto(
                arg0,
                arg0.getId().toString(),
                arg0.getTransactionDetailList().get(0).getProduct(),
                arg0.getTransactionDetailList().get(0).getAmount(),
                getTotalPrice(arg0))).toList();
        return historyItemDtos;
    }

    private Long getTotalPrice(Transaction transaction) {
        if (transaction == null) {
            return 0L;
        }
        return transaction.getTransactionDetailList().stream().map(arg0 -> arg0.getAmount() * arg0.getPrice())
                .reduce(0L, (arg0, arg1) -> arg0 + arg1);
    }

    public Transaction getTrasactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).get();
    }

    public PaginatedOrderResponse readProducts(Pageable pageable) {
        Page<Transaction> orders = transactionRepository.findAll(pageable);
        return PaginatedOrderResponse.builder()
                .numberOfItems(orders.getTotalElements())
                .numberOfPages(orders.getTotalPages())
                .orders(orders.getContent())
                .build();
    }

    public PaginatedOrderResponse filterBooks(String keyword, Pageable pageable) {
        // Page<Transaction> users =
        // transactionRepository.findAllByUsernameContains(keyword, pageable);
        // // products.and(productRepository.findAllByBrandContains(keyword, pageable));
        // // products.and(productRepository.findAllByColorContains(keyword, pageable));
        // try {
        // users.and(transactionRepository.findAllByPhoneContains(keyword, pageable));
        // } catch (Exception e) {
        // // System.out.println(e.getMessage());
        // }
        Page<Transaction> orders = transactionRepository.findAll(pageable);

        return PaginatedOrderResponse.builder()
                .numberOfItems(orders.getTotalElements())
                .numberOfPages(orders.getTotalPages())
                .orders(orders.getContent())
                .build();
    }

}
