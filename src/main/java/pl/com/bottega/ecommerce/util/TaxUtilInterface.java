package pl.com.bottega.ecommerce.util;

import pl.com.bottega.ecommerce.sales.domain.invoicing.Tax;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public interface TaxUtilInterface {
    Tax createTax(ProductType productType, Money net);
}
