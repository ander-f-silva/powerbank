package br.com.powerbank.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("account")
public record AccountAggregateRoot (
    @Id String id,
    @MappedCollection(idColumn = "id") CustomerAggregate customerAggregate,
    BigDecimal amount,
    @Column("amount_limit") BigDecimal amountLimit
) {}
