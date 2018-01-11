package pl.com.bottega.ecommerce.util;

import com.sun.istack.internal.NotNull;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Tax;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public interface TaxUtilInterface {
    Tax createTax(@NotNull ProductType productType,@NotNull Money net);
}
