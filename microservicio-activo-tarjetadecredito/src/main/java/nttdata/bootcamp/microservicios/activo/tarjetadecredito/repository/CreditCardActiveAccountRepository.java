package nttdata.bootcamp.microservicios.activo.tarjetadecredito.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents.CreditCardActiveAccount;


@Repository
public interface CreditCardActiveAccountRepository extends ReactiveMongoRepository<CreditCardActiveAccount, String> {

}
