package br.com.powerbank.account.resources;

import br.com.powerbank.account.factories.AccountAggregateRootFactory;
import br.com.powerbank.account.model.AccountAggregateRoot;
import br.com.powerbank.account.repositories.AccountRepository;
import br.com.powerbank.account.requests.AccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountsResourceTest {
  private static final String RESOURCES_ACCOUNTS = "/api/v1/accounts";

  @Mock
  private AccountRepository mockAccountRepository;

  @Mock
  private AccountAggregateRootFactory mockAccountAggregateRootFactory;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new AccountsResource(mockAccountRepository, mockAccountAggregateRootFactory)).build();
  }

  @Test
  @DisplayName("Given register an account when request is valid then save and return new resource")
  public void givenRegisterAnAccount_whenRequestIsValid_thenSaveAndReturnNewResource() throws Exception {
    var dummyRequest = """
    {
      "customer":{
        "name": "José da Silva",
        "document": "000.000.000-00",
        "birthDay": "2000-01-01",
        "address": {
            "publicPlace": "All Strete, 19",
            "complement": "Apt 2",
            "district": "All district",
            "city":"London",
            "state":"City of London"
          },
         "telephones": ["011990903256"]
      }
    }
    """;

    var id = UUID.randomUUID().toString();
    var answerAccountAggregateRoot = new AccountAggregateRoot(id, null, null, null);

    willReturn(answerAccountAggregateRoot).given(mockAccountAggregateRootFactory).create(any(AccountRequest.class));
    willReturn(answerAccountAggregateRoot).given(mockAccountRepository).save(any(AccountAggregateRoot.class));

    mockMvc
            .perform(
                    post(RESOURCES_ACCOUNTS)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(dummyRequest))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, RESOURCES_ACCOUNTS + "/" + id));

    verify(mockAccountAggregateRootFactory).create(any(AccountRequest.class));
    verify(mockAccountRepository).save(any(AccountAggregateRoot.class));
  }

  @Test
  @DisplayName("Given not register an account when request is invalid then return status request bad request")
  public void givenNotRegisterAnAccount_whenRequestIsInvalid_thenReturnStatusBadRequest() throws Exception {
    var dummyRequest = """
    {
      "customer": null
    }
    """;

    mockMvc
            .perform(
                    post(RESOURCES_ACCOUNTS)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(dummyRequest))
            .andExpect(status().isBadRequest())
            .andExpect(header().doesNotExist(HttpHeaders.LOCATION));
  }
}
