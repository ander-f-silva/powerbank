package br.com.powerbank.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Table("customer")
public record CustomerAggregate(
    @Id
    String id,
    String name,
    String document,
    LocalDate birthday,
    @MappedCollection(idColumn = "id")
    Address address,
    @MappedCollection(idColumn = "customer_id", keyColumn = "customer_id")
    Set<Telephone> telephones
) {
  public void addTelephone(String number) {
    telephones.add(new Telephone(UUID.randomUUID().toString(), number));
  }
}
