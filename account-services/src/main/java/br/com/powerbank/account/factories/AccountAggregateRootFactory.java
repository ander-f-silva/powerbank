package br.com.powerbank.account.factories;

import br.com.powerbank.account.model.AccountAggregateRoot;
import br.com.powerbank.account.requests.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountAggregateRootFactory {

  public AccountAggregateRoot create(AccountRequest accountRequest) {

    return null;
  }
}
