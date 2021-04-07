package br.com.powerbank.account.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressRequest (
        @JsonProperty("publicPlace")
        String publicPlace,
        @JsonProperty("complement")
        String complement,
        @JsonProperty("district")
        String district,
        @JsonProperty("city")
        String city,
        @JsonProperty("state")
        String state
) {}
