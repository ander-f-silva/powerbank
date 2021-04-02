package br.com.powerbank.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public record Address (
  @Id
  String id,
  @Column("public_place")
  String publicPlace,
  String complement,
  String low,
  String city,
  String state
) {}
