package br.com.powerbank.account.requests;


import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountRequest (
        @JsonProperty("customer") CustomerRequest customer
) {}
