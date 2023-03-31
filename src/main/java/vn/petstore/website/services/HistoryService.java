package vn.petstore.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.petstore.website.model.HistoryItemDto;
import vn.petstore.website.model.Transaction;

@Service
public class HistoryService {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CartService cartService;

    public List<HistoryItemDto> getHistoryItemDtos() {
        List<HistoryItemDto> historyItemDtos = transactionService.getHistoryItemDtos();

        System.out.println("list history");
        return historyItemDtos;
    }

    public Transaction getHistoryDetailsById(Long transactionId) {
        return transactionService.getTrasactionById(transactionId);
    }

    public Long getTotalPricesById(Long transactionId) {
        return getHistoryDetailsById(transactionId).getTransactionDetailList()
                .stream()
                .map(
                        arg0 -> arg0.getAmount() * arg0.getProduct().getPrice())
                .reduce(
                        0L,
                        (arg0, arg1) -> arg0 + arg1);
    }
}
