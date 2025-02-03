package com.oz.CheckingAccount;

import com.oz.CheckingAccount.Accounts.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckingAccountApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void createAccount() {
		Account account = new Account("Mary", BigDecimal.valueOf(100.00));
		ResponseEntity<Void> response = restTemplate.postForEntity("/accounts", account, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
}
