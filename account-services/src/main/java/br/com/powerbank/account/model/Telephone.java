package br.com.powerbank.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("telephones")
public record Telephone (
    @Id
    String id,
    String number
) {}
