package br.com.powerbank.account.requests;

import java.math.BigDecimal;

public record AccountRequest (CustomerRequest customer, BigDecimal amount, BigDecimal amountLimit) {}
