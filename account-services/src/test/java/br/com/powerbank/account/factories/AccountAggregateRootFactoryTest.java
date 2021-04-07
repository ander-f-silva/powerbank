package br.com.powerbank.account.factories;

import br.com.powerbank.account.requests.AccountRequest;
import br.com.powerbank.account.requests.AddressRequest;
import br.com.powerbank.account.requests.CustomerRequest;
import br.com.powerbank.account.requests.TelephoneRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountAggregateRootFactoryTest {

  private AccountAggregateRootFactory accountAggregateRootFactory = new AccountAggregateRootFactory();

  @Test
  @DisplayName(
      "Given create instance account aggregation when pass account request then generate new object")
  public void givenCreateInstanceAccountAggregation_whenPassAccountRequest_thenGenerateNewObject() {
    var dummyTelephonesRequest = new HashSet<TelephoneRequest>(1);
    dummyTelephonesRequest.add(new TelephoneRequest("000999998888"));

    var dummyAddressRequest = new AddressRequest("Street 01", "n/a", "Low", "City 01", "State 01");

    var birthDay = LocalDate.now();
    var dummyCustomerRequest =
        new CustomerRequest(
            "Jose da Silva", "00000000000", birthDay, dummyAddressRequest, dummyTelephonesRequest);

    var dummyAccountRequest =
        new AccountRequest(dummyCustomerRequest);

    var answerAccountAggregation = accountAggregateRootFactory.create(dummyAccountRequest);

    assertThat(answerAccountAggregation.id(), is(notNullValue()));
    assertThat(answerAccountAggregation.amount(), comparesEqualTo(BigDecimal.ZERO));
    assertThat(answerAccountAggregation.amountLimit(), comparesEqualTo(new BigDecimal(400.00)));

    assertThat(answerAccountAggregation.customerAggregate(), is(notNullValue()));
    assertThat(answerAccountAggregation.customerAggregate().id(), is(notNullValue()));
    assertThat(answerAccountAggregation.customerAggregate().name(), equalTo("Jose da Silva"));
    assertThat(answerAccountAggregation.customerAggregate().document(), equalTo("00000000000"));
    assertThat(answerAccountAggregation.customerAggregate().birthday(), equalTo(birthDay));

    assertThat(answerAccountAggregation.customerAggregate().address(), is(notNullValue()));
    assertThat(answerAccountAggregation.customerAggregate().address().id(), is(notNullValue()));
    assertThat(
        answerAccountAggregation.customerAggregate().address().publicPlace(), equalTo("Street 01"));
    assertThat(answerAccountAggregation.customerAggregate().address().complement(), equalTo("n/a"));
    assertThat(answerAccountAggregation.customerAggregate().address().low(), equalTo("Low"));
    assertThat(answerAccountAggregation.customerAggregate().address().city(), equalTo("City 01"));
    assertThat(answerAccountAggregation.customerAggregate().address().state(), equalTo("State 01"));

    assertThat(answerAccountAggregation.customerAggregate().telephones(), is(notNullValue()));
    assertThat(answerAccountAggregation.customerAggregate().telephones(), is(not(empty())));
    assertThat(answerAccountAggregation.customerAggregate().telephones(), hasSize(1));

    var telephones = answerAccountAggregation.customerAggregate().telephones().stream().collect(Collectors.toList());

    assertThat(telephones.get(0).id(), is(notNullValue()));
    assertThat(telephones.get(0).number(), equalTo("000999998888"));
  }

  @ParameterizedTest
  @MethodSource("accountRequestsInvalid")
  @DisplayName(
          "Given not create instance account aggregation when pass data nullable then throw error")
  public void givenNotCreateInstanceAccountAggregation_whenPassAccountRequestNullable_thenThrowError(AccountRequest accountRequestsInvalid) {
    assertThrows(IllegalArgumentException.class, () -> accountAggregateRootFactory.create(accountRequestsInvalid));
  }

  static Stream<AccountRequest> accountRequestsInvalid() {
      return Stream.of(
              null,
              new AccountRequest(null),
              new AccountRequest(new CustomerRequest("", "", null, null, null)),
              new AccountRequest(new CustomerRequest("", "", null, new AddressRequest("", "", "", "", ""), null))
      );
  }

}
