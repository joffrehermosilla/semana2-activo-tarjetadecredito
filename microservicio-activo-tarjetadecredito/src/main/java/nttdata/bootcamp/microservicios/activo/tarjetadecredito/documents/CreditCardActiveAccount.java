package nttdata.bootcamp.microservicios.activo.tarjetadecredito.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "active-account-credit-card")
public class CreditCardActiveAccount {
	// el id será el número de transacción que se anexará a la cuenta para consultar
	// saldos, hacer transferencias y pagos
	@Id
	private String id;

	// si es tipo cuenta persona o empresarial
	private String namebyaccount;

	// cantidad de credito que tiene la tarjeta
	private Double allowedperclient;

	private boolean enabledtouse;

	private int paymentdays;

	private Double currentbalance;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}
