package vn.petstore.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.petstore.website.model.HistoryItemDto;

@Service
public class HistoryService {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CartService cartService;

    public List<HistoryItemDto> getHistoryItemDtos() {
        List<HistoryItemDto> historyItemDtos = transactionService.getHistoryItemDtos();

        System.out.println("list history");
        historyItemDtos.forEach(System.out::println);
        return historyItemDtos;
    }
}
