package com.example.bank.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.bank.entities.Account;
import com.example.bank.services.impl.AccountServiceImpl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// 1. TDD normale
// 2. logique de Mocking (ExtendWith(MockitoExtension.class) =>  Mock [dépôt/service] => Injects [contrôleur])
// 3. instanciation & usage d'un MockMvc
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
  // simule la connexion http (bien en CICD)
  private MockMvc mvc;
  private Account account;

  @Mock
  private AccountServiceImpl accountServiceImpl;
  
  @InjectMocks
  private AccountController accountController;
  
  @BeforeEach
  public void setup(){
    mvc = MockMvcBuilders.standaloneSetup(accountController)
             .build();
  }

  // l'utilisation du mockmvc demande à la méthode de test de pourvoir lancer une exception
  @Test
  void testWithdrawal() throws Exception {
    String title = "truc";
    Float balance = 500.f;
    Float overdraft = -100.f;
    int amount = 100;
    account = new Account(title, balance, overdraft);
    
    // simulation des méthodes du contrôleur réél
    // déjà testés avec les tests unitaires !!!
    given(accountServiceImpl.getAccountByTitle(title)).willReturn(account);
    account.setBalance(balance - amount);
    given(accountServiceImpl.withdrawal(account, (float)amount)).willReturn(account);
    
    MockHttpServletResponse response = mvc
      .perform(put("/api/accounts/withdrawal/" + title + "/" + amount))
      .andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("\"balance\":" + Float.toString(balance - amount));
  }
}
