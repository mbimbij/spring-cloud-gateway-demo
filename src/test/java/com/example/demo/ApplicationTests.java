package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {
				"routes.route1=http://localhost:${wiremock.server.port}/route1",
				"routes.route2=http://localhost:${wiremock.server.port}/route2"
		}
)
@AutoConfigureWireMock(port = 0)
class ApplicationTests {
	@Autowired
	WebTestClient webClient;

	@BeforeAll
	static void beforeAll() {
		stubFor(
				get(urlEqualTo("/route1"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody("{\"hello\":\"route1\"}")
				));
		stubFor(
				get(urlEqualTo("/route2"))
						.willReturn(aResponse()
								.withHeader("Content-Type", "application/json")
								.withBody("{\"hello\":\"route2\"}")
						));
	}

	@Test
	void testRoute1() {
		webClient.get()
				.uri("/route1")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json("{\"hello\":\"route1\"}");
	}

	@Test
	void testRoute2() {
		webClient.get()
				.uri("/route2")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json("{\"hello\":\"route2\"}");
	}
}
