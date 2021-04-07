package br.com.powerbank.account.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public record CustomerRequest(
        @NotBlank
        @JsonProperty("name")
        String name,
        @JsonProperty("document")
        String document,
        @JsonProperty("birthDay")
        LocalDate birthDay,
        @NotNull
        @JsonProperty("address")
        AddressRequest address,
        @JsonProperty("telephones")
        Set<TelephoneRequest> telephones
) {}
