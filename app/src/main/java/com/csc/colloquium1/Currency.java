package com.csc.colloquium1;

import android.content.ContentValues;

public class Currency {
    public final String name;
    public final double rate;

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues(2);
        values.put(CurrenciesTable.COLUMN_NAME, name);
        values.put(CurrenciesTable.COLUMN_VALUE, rate);
        return values;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
