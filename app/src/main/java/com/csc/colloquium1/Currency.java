package com.csc.colloquium1;

import android.content.ContentValues;
import android.database.Cursor;

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

    public static Currency from(Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(CurrenciesTable.COLUMN_NAME));
        Double rate = cursor.getDouble(cursor.getColumnIndex(CurrenciesTable.COLUMN_VALUE));
        return new Currency(name, rate);
    }
}
