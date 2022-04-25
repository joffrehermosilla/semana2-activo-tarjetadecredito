package nttdata.bootcamp.microservicios.activo.tarjetadecredito.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents.CreditCardActiveAccount;
import nttdata.bootcamp.microservicios.activo.tarjetadecredito.repository.CreditCardActiveAccountRepository;
import nttdata.bootcamp.microservicios.activo.tarjetadecredito.services.CreditCardActiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
public class CredictCardActiveAccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CredictCardActiveAccountController.class);
	@Autowired
	CreditCardActiveAccountService service;
	
	@GetMapping("/all")
	public Flux<CreditCardActiveAccount> searchAll() {
	Flux<CreditCardActiveAccount> per = service.findAlls(); 
	
	LOGGER.info("Credit Card account registered: "+ per); return per; }

	@GetMapping("/id/{id}")
	public Mono<CreditCardActiveAccount> searchById(@PathVariable String id) {
	LOGGER.info("Credit Card account id: " + service.findById(id) + " con codigo: " + id);
	return service.findById(id);
	}

	@PostMapping("/create-creditcard-account")
	public Mono<CreditCardActiveAccount> createCreditCardAccount(@Valid @RequestBody CreditCardActiveAccount creditCardAccount) {
	LOGGER.info("Credit Card account create: " + service.saves(creditCardAccount));
	return service.saves(creditCardAccount);
	}
	
	@PutMapping("/update-creditcard-account/{id}")
	public ResponseEntity<Mono<?>> updateCreditCard(@PathVariable String id,
			@Valid @RequestBody CreditCardActiveAccount creditCard) {
		Mono.just(creditCard).doOnNext(t -> {
			creditCard.setId(id);
			t.setCreateAt(new Date());
		
		}).onErrorReturn(creditCard).onErrorResume(e -> Mono.just(creditCard))
			.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));
		
		Mono<CreditCardActiveAccount> newcreditCardAccount = service.saves(creditCard);
		
		if (newcreditCardAccount != null) {
		return new ResponseEntity<>(newcreditCardAccount, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new CreditCardActiveAccount()), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@DeleteMapping("/eliminar-creditcard-activeaccount/{id}")
	public ResponseEntity<Mono<Void>> deleteCreditCardActiveAccount(@PathVariable String id) {
	CreditCardActiveAccount creditcard = new CreditCardActiveAccount();
	creditcard .setId(id);
	Mono<CreditCardActiveAccount> newCreditCardActiveAccount = service.findById(id);
	newCreditCardActiveAccount.subscribe();
	Mono<Void> test = service.delete(creditcard );
	test.subscribe();
	return ResponseEntity.noContent().build();
	}
}
