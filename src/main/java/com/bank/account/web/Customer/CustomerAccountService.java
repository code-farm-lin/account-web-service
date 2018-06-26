package com.bank.account.web.Customer;

import com.bank.account.web.account.Account;
import com.bank.account.web.transaction.Transaction;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Date;

import static java.util.Arrays.asList;

@Service
@Log
public class CustomerAccountService {

    @Value("${bank.account.serivce.uri}")
    private String accountSeriveURI;

    public CustomerAccountDetails findCustomerAccountDetails(String customerid, boolean withTransactionDetails) {
        WebClient webClient = WebClient.builder()
                .baseUrl(accountSeriveURI )
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        ClientResponse details =  webClient.get().uri("customers/query?" + customerid).exchange().block();
        //TODO
        if(details.statusCode().isError()) {
          log.info("no data return which may due to id is not set in database, ignore for it now for demo purpose ");
          return getDefaultForTersting();
        }
        return details.bodyToMono(CustomerAccountDetails.class).block();

    }

    private CustomerAccountDetails getDefaultForTersting() {


        Customer customer = Customer.builder().firstName("Liwen").lastName("Lin").id("1234").build();
        Transaction transaction1 = Transaction.builder().id("1234").transactionAmount(BigDecimal.valueOf(12413)).transactionDate(new Date()).direction("IN").accountID("1000").build();
        Transaction transaction2 = Transaction.builder().id("1234").transactionAmount(BigDecimal.valueOf(12413)).transactionDate(new Date()).direction("IN").accountID("1002").build();

        Account currentAccount1 = Account.builder()
                .accountNumber(123456789L)
                .accountType("Current Account")
                .balance(BigDecimal.valueOf(12345.12))
                .openBalance(BigDecimal.valueOf(1000.00))
                .openDate(new Date())
                .sortCode(12342L)
                .id("10000")
                .transactions(asList(transaction1))
                .build();
        Account currentAccount2 = Account.builder()
                .accountNumber(123456780L)
                .accountType("Saving Account")
                .balance(BigDecimal.valueOf(1222))
                .openBalance(BigDecimal.valueOf(250))
                .openDate(new Date())
                .sortCode(12342L)
                .id("10001")
                .transactions(asList(transaction2))
                .build();

        Account currentAccount3 = Account.builder()
                .accountNumber(123456781L)
                .accountType("Regular Saving Account")
                .balance(BigDecimal.valueOf(5000))
                .openBalance(BigDecimal.valueOf(250))
                .openDate(new Date())
                .sortCode(12342L)
                .id("10002")
                .build();

        return CustomerAccountDetails.builder().customer(customer).accounts(asList(currentAccount1, currentAccount2, currentAccount3)).build();

    }
}
