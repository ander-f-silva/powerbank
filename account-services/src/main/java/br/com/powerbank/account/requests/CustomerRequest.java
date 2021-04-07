package br.com.powerbank.account.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
