package br.com.powerbank.account.repositories;

import br.com.powerbank.account.model.AccountAggregateRoot;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountAggregateRoot, String> {}
