package vn.petstore.website.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.petstore.website.model.HistoryItemDto;
import vn.petstore.website.model.Transaction;
import vn.petstore.website.services.HistoryService;

@Controller
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @RequestMapping(value = { "/history" }, method = RequestMethod.GET)
    public String getHistory(Model model) {

        List<HistoryItemDto> historyItemDtos = historyService.getHistoryItemDtos();
        model.addAttribute("historyItemDtos", historyItemDtos);
        return "history";
    }

    @RequestMapping(value = { "/history/detail" }, method = RequestMethod.GET)
    public String checkoutDetail(Model model, @RequestParam("id") Optional<Long> transactionId) {

        Transaction transaction = historyService.getHistoryDetailsById(transactionId.get());
        Long total = historyService.getTotalPricesById(transactionId.get());
        model.addAttribute("transaction", transaction);
        model.addAttribute("totalPrice", total);
        return "detail";
    }
}
