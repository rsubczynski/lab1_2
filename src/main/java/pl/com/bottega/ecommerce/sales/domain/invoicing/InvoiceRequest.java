package pl.com.bottega.ecommerce.sales.domain.invoicing;

import com.sun.istack.internal.NotNull;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InvoiceRequest {

    private ClientData client;
    private List<RequestItem> items = new ArrayList<RequestItem>();

    public InvoiceRequest(@NotNull
            ClientData client) {
        this.client = client;
    }

    public void add(@NotNull RequestItem item) {
        items.add(item);
    }

    public ClientData getClient() {
        return client;
    }

    public Collection<RequestItem> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public ClientData getClientData() {
        return client;
    }
}
