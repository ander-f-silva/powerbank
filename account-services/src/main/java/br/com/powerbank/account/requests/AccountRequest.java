package br.com.powerbank.account.requests;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record AccountRequest (
       @NotNull @Valid @JsonProperty("customer")
       CustomerRequest customer
) {}
