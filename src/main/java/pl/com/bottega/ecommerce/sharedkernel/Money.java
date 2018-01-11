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
package pl.com.bottega.ecommerce.sharedkernel;

import com.sun.istack.internal.NotNull;
import org.fest.util.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {

    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private BigDecimal denomination;
    private String currencyCode;

    protected Money() {
    }

    public Money(@NotNull
            BigDecimal denomination,@NotNull Currency currency) {
        this(denomination, currency.getCurrencyCode());
    }

    private Money(@NotNull BigDecimal denomination,@NotNull String currencyCode) {
        this.denomination = denomination.setScale(2, RoundingMode.HALF_EVEN);
        this.currencyCode = currencyCode;
    }

    public Money(@NotNull BigDecimal denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    public Money(@NotNull double denomination,@NotNull Currency currency) {
        this(new BigDecimal(denomination), currency.getCurrencyCode());
    }

    public Money(@NotNull double denomination,@NotNull String currencyCode) {
        this(new BigDecimal(denomination), currencyCode);
    }

    public Money(@NotNull double denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    public Money multiplyBy(@NotNull double multiplier) {
        return multiplyBy(new BigDecimal(multiplier));
    }

    public Money multiplyBy(@NotNull BigDecimal multiplier) {
        return new Money(denomination.multiply(multiplier), currencyCode);
    }

    public Money add(@NotNull Money money) {
        if (!compatibleCurrency(money)) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        return new Money(denomination.add(money.denomination), determineCurrencyCode(money));
    }

    public Money subtract(@NotNull Money money) {
        if (!compatibleCurrency(money))
            throw new IllegalArgumentException("Currency mismatch");

        return new Money(denomination.subtract(money.denomination), determineCurrencyCode(money));
    }

    /**
     * Currency is compatible if the same or either money object has zero value.
     */
    private boolean compatibleCurrency(@NotNull Money money) {
        return isZero(denomination) || isZero(money.denomination) || currencyCode.equals(money.getCurrencyCode());
    }

    private boolean isZero(@NotNull BigDecimal testedValue) {
        return BigDecimal.ZERO.compareTo(testedValue) == 0;
    }

    /**
     * @return currency from this object or otherCurrencyCode. Preferred is the
     * one that comes from Money that has non-zero value.
     */
    private Currency determineCurrencyCode(@NotNull Money otherMoney) {
        String resultingCurrencyCode = isZero(denomination) ? otherMoney.currencyCode : currencyCode;
        return Currency.getInstance(resultingCurrencyCode);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Currency getCurrency() {
        return Currency.getInstance(currencyCode);
    }

    public boolean greaterThan(Money other) {
        return denomination.compareTo(other.denomination) > 0;
    }

    public boolean lessThan(Money other) {
        return denomination.compareTo(other.denomination) < 0;
    }

    public boolean lessOrEquals(Money other) {
        return denomination.compareTo(other.denomination) <= 0;
    }

    @Override public String toString() {
        return String.format("%0$.2f %s", denomination, getCurrency().getSymbol());
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
        result = prime * result + ((denomination == null) ? 0 : denomination.hashCode());
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        return compatibleCurrency(other) && Objects.areEqual(denomination, other.denomination);
    }

}
