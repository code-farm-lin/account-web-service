package com.bank.account.web.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping("/customers/{customerid}")
    public String findAccountDetails( @PathVariable("customerid") String customerid , Model model ) {
        CustomerAccountDetails customerAccountDetails = customerAccountService.findCustomerAccountDetails(customerid, true);
        model.addAttribute("accountdetails", customerAccountDetails);
        return "/accounts";
    }
}
