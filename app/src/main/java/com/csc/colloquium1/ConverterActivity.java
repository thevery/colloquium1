package com.csc.colloquium1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ConverterActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_RATE = "rate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        final double rate = getIntent().getDoubleExtra(EXTRA_RATE, -1);
        final EditText editText1 = (EditText) findViewById(R.id.et1);
        final EditText editText2 = (EditText) findViewById(R.id.et2);

        editText1.setHint(name);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editText1.isFocused()) return;

                try {
                    double v = Double.parseDouble(editText1.getText().toString());
                    editText2.setText(String.valueOf(v * rate));
                } catch (Throwable throwable) {
                    editText2.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editText2.isFocused()) return;
                try {
                    double v = Double.parseDouble(editText2.getText().toString());
                    editText1.setText(String.valueOf(v / rate));
                } catch (Throwable throwable) {
                    editText1.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
