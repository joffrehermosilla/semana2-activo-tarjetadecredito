package nttdata.bootcamp.microservicios.activo.tarjetadecredito.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents.CreditCardActiveAccount;
import nttdata.bootcamp.microservicios.activo.tarjetadecredito.services.CreditCardActiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CreditCardActiveAccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardActiveAccountController.class);

	@Autowired
	private CreditCardActiveAccountService service;

	@GetMapping("/all")
	public Flux<CreditCardActiveAccount> searchAll() {
		Flux<CreditCardActiveAccount> business = service.findAlls();
		LOGGER.info("CREDIT CARD ACTIVE ACCOUNT registered: " + business);
		return business;
	}

	@GetMapping("/id/{id}")
	public Mono<CreditCardActiveAccount> searchById(@PathVariable String id) {
		LOGGER.info("Credit Card Active Account id: " + service.findById(id) + " code : " + id);
		return service.findById(id);
	}

	@PostMapping("/create-creditcard-active-account")
	public Mono<CreditCardActiveAccount> createPersonalAsset(
			@Valid @RequestBody CreditCardActiveAccount creditCardActiveAccount) {
		LOGGER.info("CREDIT CARD create: " + service.saves(creditCardActiveAccount));
		return service.saves(creditCardActiveAccount);
	}

}
