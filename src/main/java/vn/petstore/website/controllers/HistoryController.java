package vn.petstore.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.petstore.website.model.HistoryItemDto;
import vn.petstore.website.services.HistoryService;

@Controller
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @RequestMapping(value = { "/history" }, method = RequestMethod.GET)
    public String getHistory(Model model) {

        List<HistoryItemDto> historyItemDtos = historyService.getHistoryItemDtos();
        System.out.println("history onnn");
        model.addAttribute("historyItemDtos", historyItemDtos);
        return "history";
    }
}
