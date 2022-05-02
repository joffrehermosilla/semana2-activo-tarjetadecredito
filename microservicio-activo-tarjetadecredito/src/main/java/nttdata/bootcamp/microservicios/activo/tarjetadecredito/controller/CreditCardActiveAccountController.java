package nttdata.bootcamp.microservicios.activo.tarjetadecredito.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	CreditCardActiveAccountService service;

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("credit_card", service.findAlls());
		return ResponseEntity.ok(response);

	}

	@GetMapping("/all")
	public Flux<CreditCardActiveAccount> searchAll() {
		Flux<CreditCardActiveAccount> credit = service.findAlls();
		LOGGER.info("CREDIT CARD registered: " + credit);
		return credit;
	}

	@GetMapping("/id/{id}")
	public Mono<CreditCardActiveAccount> searchById(@PathVariable String id) {
		LOGGER.info("Credit Card id: " + service.findById(id) + " code : " + id);
		return service.findById(id);
	}

	@PostMapping("/create-creditcard-asset")
	public Mono<CreditCardActiveAccount> createCardActiveAccount(
			@Valid @RequestBody CreditCardActiveAccount creditCardActive) {
		LOGGER.info("CREDIT CARD create: " + service.saves(creditCardActive));
		Mono.just(creditCardActive).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(creditCardActive).onErrorResume(e -> Mono.just(creditCardActive))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));

		Mono<CreditCardActiveAccount> newCreditCardActive = service.saves(creditCardActive);

		return newCreditCardActive;
	}

	@PutMapping("/update-creditcard-asset/{id}")
	public ResponseEntity<Mono<?>> updateCreditActiveAccount(@PathVariable String id,
			@Valid @RequestBody CreditCardActiveAccount creditCardActive) {
		Mono.just(creditCardActive).doOnNext(t -> {
			creditCardActive.setId(id);
			t.setCreateAt(new Date());
		}).onErrorReturn(creditCardActive).onErrorResume(e -> Mono.just(creditCardActive))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));
		Mono<CreditCardActiveAccount> newcreditCardAccount = service.saves(creditCardActive);
		if (newcreditCardAccount != null) {
			return new ResponseEntity<>(newcreditCardAccount, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new CreditCardActiveAccount()), HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/delete-creditcard-asset/{id}")
	public ResponseEntity<Mono<Void>> deleteBusinessAsset(@PathVariable String id) {
		CreditCardActiveAccount creditCardActive = new CreditCardActiveAccount();
		creditCardActive.setId(id);
		Mono<CreditCardActiveAccount> newBusinessAsset = service.findById(id);
		newBusinessAsset.subscribe();
		Mono<Void> test = service.delete(creditCardActive);
		test.subscribe();
		return ResponseEntity.noContent().build();
	}

}
