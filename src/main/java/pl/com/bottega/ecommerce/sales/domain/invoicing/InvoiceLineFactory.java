package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class InvoiceLineFactory {
    public InvoiceLine createInvoiceLine(ProductData productData, int quantity, Money net, Tax tax) {
        return new InvoiceLine(productData, quantity, net, tax);
    }
}
