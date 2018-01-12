package pl.com.bottega.ecommerce.sales.domain.payment;

import com.sun.istack.internal.NotNull;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class PaymentFactory {
	public Payment createPayment(@NotNull ClientData clientData,@NotNull Money amount) {
		Id aggregateId = Id.generate();
		return new Payment(aggregateId, clientData, amount);
	}
}
