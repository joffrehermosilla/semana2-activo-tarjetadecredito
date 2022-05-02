package nttdata.bootcamp.microservicios.activo.tarjetadecredito.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents.CreditCardActiveAccount;
import nttdata.bootcamp.microservicios.activo.tarjetadecredito.repository.CreditCardActiveAccountRepository;
import nttdata.bootcamp.microservicios.activo.tarjetadecredito.services.CreditCardActiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardActiveAccountServiceImpl implements CreditCardActiveAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardActiveAccountServiceImpl.class);

	@Autowired
	CreditCardActiveAccountRepository repository;

	@Override
	public Mono<CreditCardActiveAccount> findById(String id) {

		return repository.findById(id);
	}

	@Override
	public Flux<CreditCardActiveAccount> findAlls() {

		return repository.findAll();
	}

	@Override
	public Mono<CreditCardActiveAccount> saves(CreditCardActiveAccount document) {

		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(CreditCardActiveAccount document) {

		return repository.delete(document);
	}

}
