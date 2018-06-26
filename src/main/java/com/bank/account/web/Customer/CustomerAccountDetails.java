package com.bank.account.web.Customer;

import com.bank.account.web.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAccountDetails {
    private Customer customer;
    private List<Account> accounts;
}
