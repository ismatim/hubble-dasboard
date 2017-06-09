package com.tsoftlatam.tab.frontend.sources.hpbac.controllers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.ProfilesService;
import com.tsoftlatam.tab.frontend.sources.hpbac.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private ProfilesService profilesService;

    //Devuelve la lista de transacciones
    @GetMapping("/sources/hpbac/transactions")
    public ModelAndView list(HttpServletRequest request){
        List<Transaction> transactions= (List<Transaction>) transactionsService.findAllTransactions();
        List<String> profileName = new ArrayList<>();

        for (Transaction transaction: transactions) {
            profileName.add(profilesService.findProfileById(transaction.getProfileId()).getProfileName());
        }

        ModelAndView modelAndView = new ModelAndView();
        request.setAttribute("profileName", profileName);
        request.setAttribute("transactions", transactions);
        modelAndView.setViewName("/sources/hpbac/transactions/list");
        return modelAndView;
    }

    //Devuelve una transacción específica
    @GetMapping("/sources/hpbac/transactions/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Transaction transaction = transactionsService.findTransactionById(id);
        String profileName = profilesService.findProfileById(transaction.getProfileId()).getProfileName();
        request.setAttribute("profileName", profileName);
        request.setAttribute("transaction", transaction);
        modelAndView.setViewName("/sources/hpbac/transactions/edit");
        return modelAndView;
    }

    //Actualización de un registro
    @PostMapping("/sources/hpbac/transactions/update/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){

        Transaction transaction = transactionsService.updateTransaction(id ,request.getParameter("transactionName"),
                request.getParameter("displayName"));


        request.setAttribute("transaction", transaction);

        try {
            response.sendRedirect("/sources/hpbac/transactions/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
