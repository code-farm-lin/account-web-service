package com.bank.account.web.account;

import com.bank.account.web.Customer.CustomerAccountDetails;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@Log
public class AccountController {

    @Value("${bank.account.serivce.uri}")
    private String accountServiceURI;

    @PostMapping("/customers/{customerid}/newaccount")
    public String findAccountDetails(@PathVariable("customerid") String customerid , @ModelAttribute Account account ) {
        account.setCustomerID(customerid);
        WebClient webclient = WebClient.builder().baseUrl(accountServiceURI)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
          webclient.post()
                .uri("/accounts")
                .body(Mono.just(account), Account.class)
                .exchange()
                .map(this::processResponse)
                .block();
          return "redirect:/customers/" + customerid;

    }

    @GetMapping("/customers/{customerid}/account")
    public String greetingForm(@PathVariable("customerid") String customerid, Model model) {

        model.addAttribute("account", Account.builder().customerID(customerid).build());
        return "new-account";
    }


    private Mono<Account> processResponse(ClientResponse clientResponse) {
        if(clientResponse.statusCode().isError()) {
            //TODO: handle error
            log.warning("Error post transction");
            throw new RuntimeException("unable to post transaction");
        } else {
            return clientResponse.bodyToMono(Account.class);
        }

    }
}

