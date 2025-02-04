package com.oz.CheckingAccount;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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

	@Test
	void getAccount() {
		Account account = new Account("oz", BigDecimal.valueOf(350.99));
		ResponseEntity<Void> response = restTemplate.postForEntity("/accounts", account, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<String> responseAccount = restTemplate.getForEntity("/accounts/oz", String.class);
		assertThat(responseAccount.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext doc = JsonPath.parse(responseAccount.getBody());
		Number id = doc.read("$.id");
		assertThat(id).isNotNull();

		String name = doc.read("$.name");
		assertThat(name).isEqualTo("oz");

		Double balance = doc.read("$.balance");
		assertThat(balance).isEqualTo(350.99);
	}
}
