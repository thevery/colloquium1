package com.csc.colloquium1;

import android.provider.BaseColumns;

interface CurrenciesTable extends BaseColumns {
    String TABLE_NAME = "currencies";

    String COLUMN_NAME = "name";
    String COLUMN_VALUE = "value";
}
