package nttdata.bootcamp.microservicios.activo.tarjetadecredito.services;

import nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents.CreditCardActiveAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardActiveAccountService {
	public Mono<CreditCardActiveAccount> findById(String id);

	public Flux<CreditCardActiveAccount> findAlls();

	public Mono<CreditCardActiveAccount> saves(CreditCardActiveAccount document);

	public Mono<Void> delete(CreditCardActiveAccount document);
}
