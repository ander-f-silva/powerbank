package br.com.powerbank.account.requests;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AccountRequest (
        @JsonProperty("customer") CustomerRequest customer
) {}
