package br.com.powerbank.account.requests;

public record AddressRequest (String publicPlace, String complement, String low, String city, String state) {}
