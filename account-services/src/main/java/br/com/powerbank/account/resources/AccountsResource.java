package br.com.powerbank.account.resources;

import br.com.powerbank.account.factories.AccountAggregateRootFactory;
import br.com.powerbank.account.repositories.AccountRepository;
import br.com.powerbank.account.requests.AccountRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/accounts")
class AccountsResource {
  private final AccountRepository accountRepository;
  private final AccountAggregateRootFactory accountAggregateRootFactory;

  public AccountsResource(
      final AccountRepository accountRepository,
      final AccountAggregateRootFactory accountAggregateRootFactory) {
    this.accountRepository = accountRepository;
    this.accountAggregateRootFactory = accountAggregateRootFactory;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> registerNewAccount(@RequestBody final AccountRequest accountRequest) {
    var accountAggregateRoot = accountAggregateRootFactory.create(accountRequest);

    accountAggregateRoot = accountRepository.save(accountAggregateRoot);

    return ResponseEntity
            .created(URI.create("/api/v1/accounts/" + accountAggregateRoot.id()))
            .build();
  }
}
