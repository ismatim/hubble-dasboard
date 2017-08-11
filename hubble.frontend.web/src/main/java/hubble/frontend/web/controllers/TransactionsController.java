package hubble.frontend.web.controllers;

import hubble.frontend.managers.interfaces.AvailabilityManager;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.models.aggregations.AvailabilityTransactionAvg;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;
import hubble.frontend.managers.models.collections.Availability;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.entities.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionsController {

    @Autowired
    private AvailabilityManager availabilityManager;
    @Autowired
    private PerformanceManager performanceManager;

    @GetMapping("/Transfer/Applications")
    public ModelAndView getTransferApplication(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        List<BusinessApplication> applications = new ArrayList();
        applications = availabilityManager.findAllApplications();
        req.setAttribute("regs", applications);
        mav.setViewName("/Transfer/ApplicationTransferCards");

        return mav;
    }

    @GetMapping("/{applicationId}/transactions")
    public ModelAndView getTransactionsFromApplicationId(HttpServletRequest req,
            @PathVariable String applicationId ){
        
        ModelAndView mav = new ModelAndView();
        List<Transaction> transactions = availabilityManager.findTransactionsByApplication(applicationId);
        List<Availability> availabilitiesLastHour = availabilityManager.findLastHourAvailabilitiesByApplicationId(applicationId);
        List<Availability> availabilitiesLast10Minutes = availabilityManager.findLast10MinutesAvailabilitiesByApplicationId(applicationId);
        List<Performance> performancesLastHour = performanceManager.findLastHourPerformanceByApplicationId(applicationId);
        List<Performance> performancesLast10Minutes = performanceManager.findLast10MinutesPerformanceByApplicationId(applicationId);
        List<AvailabilityTransactionAvg> availabilityTransactionsLastHourAvgs = new ArrayList();
        List<AvailabilityTransactionAvg> availabilityTransactionsLast10MinutesAvgs = new ArrayList();
        List<PerformanceTransactionAvg> performanceTransactionsLastHourAvgs = new ArrayList();
        List<PerformanceTransactionAvg> performanceTransactionsLast10MinutesAvgs = new ArrayList();

        for (Transaction transaction : transactions) {
            availabilityTransactionsLastHourAvgs.add(availabilityManager.findLastHourAverageByTransaction(transaction.getId()));
            availabilityTransactionsLast10MinutesAvgs.add(availabilityManager.findLast10MinutesAverageByTransaction(transaction.getId()));
            performanceTransactionsLast10MinutesAvgs.add(performanceManager.findLast10MinutesAverageByTransaction(transaction.getId()));
            performanceTransactionsLastHourAvgs.add(performanceManager.findLastHourAverageByTransaction(transaction.getId()));
        }

        req.setAttribute("transactions", transactions);
        req.setAttribute("availabilityLastHourAvgs", availabilityTransactionsLastHourAvgs);
        req.setAttribute("availabilityLast10MinutesAvgs", availabilityTransactionsLast10MinutesAvgs);
        req.setAttribute("performanceLastHourAvgs", performanceTransactionsLastHourAvgs);
        req.setAttribute("performanceLast10MinutesAvgs", performanceTransactionsLast10MinutesAvgs);
        req.setAttribute("availabilitiesLastHour", availabilitiesLastHour);
        req.setAttribute("availabilitiesLast10Minutes", availabilitiesLast10Minutes);
        req.setAttribute("performancesLastHour", performancesLastHour);
        req.setAttribute("performancesLast10Minutes", performancesLast10Minutes);
        req.setAttribute("now", new Date());

        for (Performance perf : performancesLastHour){
            System.out.println(perf.getResponseTime());
        }
        
        mav.setViewName("transactionCards");
        return mav;
    }

}
