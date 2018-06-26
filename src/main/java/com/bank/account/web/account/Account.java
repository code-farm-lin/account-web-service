package com.bank.account.web.account;

import com.bank.account.web.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String id;
    private String customerID;
    private Long accountNumber;
    private Long sortCode;
    private String accountType;
    private BigDecimal balance;
    private Date openDate;
    private BigDecimal openBalance;
    private List<Transaction> transactions;
}
