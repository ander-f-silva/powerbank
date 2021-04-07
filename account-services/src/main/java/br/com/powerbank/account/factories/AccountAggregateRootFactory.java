package br.com.powerbank.account.factories;

import br.com.powerbank.account.model.AccountAggregateRoot;
import br.com.powerbank.account.model.Address;
import br.com.powerbank.account.model.CustomerAggregate;
import br.com.powerbank.account.model.Telephone;
import br.com.powerbank.account.requests.AccountRequest;
import br.com.powerbank.account.requests.AddressRequest;
import br.com.powerbank.account.requests.CustomerRequest;
import br.com.powerbank.account.requests.TelephoneRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountAggregateRootFactory {

  public AccountAggregateRoot create(AccountRequest accountRequest) {
    if (Objects.isNull(accountRequest)) {
      throw  new IllegalArgumentException("account not found");
    }

    if (Objects.isNull(accountRequest.customer())) {
      throw  new IllegalArgumentException("customer not found");
    }

    if (Objects.isNull(accountRequest.customer().address())) {
      throw  new IllegalArgumentException("address not found");
    }

    if (Objects.isNull(accountRequest.customer().telephones())) {
      throw  new IllegalArgumentException("telephone not found");
    }

    var customerAggregate = buildCustomer(accountRequest.customer());

    var accountAggregateRoot =
        new AccountAggregateRoot(
            UUID.randomUUID().toString(),
            customerAggregate,
            BigDecimal.ZERO,
            new BigDecimal(400.00)); //TODO: configuration to recebi this value

    return accountAggregateRoot;

  }

  private CustomerAggregate buildCustomer(CustomerRequest customerRequest) {
    var address = buildAddress(customerRequest.address());
    var telephones = buildTelephone(customerRequest.telephones());

    return new CustomerAggregate(
        UUID.randomUUID().toString(),
        customerRequest.name(),
        customerRequest.document(),
        customerRequest.birthDay(),
        address,
        telephones);
  }

  private Address buildAddress(AddressRequest addressRequest) {
    return new Address(
        UUID.randomUUID().toString(),
        addressRequest.publicPlace(),
        addressRequest.complement(),
        addressRequest.district(),
        addressRequest.city(),
        addressRequest.state());
  }

  private Set<Telephone> buildTelephone(Set<TelephoneRequest> telephoneRequests) {
    return telephoneRequests.stream()
        .map(
            telephoneRequest ->
                new Telephone(
                        UUID.randomUUID().toString(),
                        telephoneRequest.number()
                )
        )
        .collect(Collectors.toSet());
  }
}
