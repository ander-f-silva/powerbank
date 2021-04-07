package br.com.powerbank.account.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Set;

public record CustomerRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("document")
        String document,
        @JsonProperty("birthDay")
        LocalDate birthDay,
        @JsonProperty("address")
        AddressRequest address,
        @JsonProperty("telephones")
        Set<TelephoneRequest> telephones
) {}
