/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.util.TaxUtilInterface;

public class BookKeeper {

    InvoiceFactory invoiceFactory = new InvoiceFactory();
    InvoiceLineFactory invoiceLineFactory = new InvoiceLineFactory();

    public Invoice issuance(InvoiceRequest invoiceRequest, TaxUtilInterface taxInterface) {

        Invoice invoice = invoiceFactory.createInvoice(invoiceRequest.getClientData());

        for (RequestItem item : invoiceRequest.getItems()) {
            Money net = item.getTotalCost();
            Tax tax = taxInterface.createTax(item.getProductData().getType(), net);
            InvoiceLine invoiceLine = invoiceLineFactory.createInvoiceLine(item.getProductData(), item.getQuantity(),
                    net, tax);
            invoice.addItem(invoiceLine);
        }

        return invoice;
    }

}
