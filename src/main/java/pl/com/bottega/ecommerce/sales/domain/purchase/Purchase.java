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
package pl.com.bottega.ecommerce.sales.domain.purchase;

import com.sun.istack.internal.NotNull;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Purchase {

    private List<PurchaseItem> items;
    private boolean paid;
    private ClientData clientData;
    private Date purchaseDate;
    private Money totalCost;
    private Id aggregateId;

    @SuppressWarnings("unused") private Purchase() {
    }

    Purchase(@NotNull Id aggregateId,@NotNull ClientData clientData,@NotNull List<PurchaseItem> items,
            @NotNull Date purchaseDate,@NotNull boolean paid,
            Money totalCost) {
        this.aggregateId = aggregateId;
        this.clientData = clientData;
        this.items = items;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
        this.totalCost = totalCost;
    }

    public void confirm() {
        paid = true;
    }

    public boolean isPaid() {
        return paid;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public Collection<PurchaseItem> getItems() {
        return (Collection<PurchaseItem>) Collections.unmodifiableCollection(items);
    }

    public Id getAggregateId() {
        return this.aggregateId;
    }

}
