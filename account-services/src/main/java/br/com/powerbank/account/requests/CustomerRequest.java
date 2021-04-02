package br.com.powerbank.account.requests;

import java.time.LocalDate;
import java.util.Set;

public record CustomerRequest(String name, String document, LocalDate birthday, AddressRequest address,  Set<TelephoneRequest> telephones) {}
