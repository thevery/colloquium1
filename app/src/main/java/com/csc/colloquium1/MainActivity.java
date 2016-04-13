package com.csc.colloquium1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Cursor cursor = getContentResolver().query(ReaderContentProvider.CURRENCIES_URI, null, null, null, null);
                    System.out.println("cursor.getCount() = " + cursor.getCount());
                    cursor.close();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse("http://www.cbr.ru/scripts/XML_daily.asp");
                    NodeList valuteList = document.getElementsByTagName("Valute");

                    int length = valuteList.getLength();
                    List<Currency> currencyList = new ArrayList<Currency>(length);

                    for (int i = 0; i < length; i++) {
                        Node node = valuteList.item(i);
                        int nominal = Integer.parseInt(((Element) node).getElementsByTagName("Nominal").item(0).getTextContent());

                        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                        String value = ((Element) node).getElementsByTagName("Value").item(0).getTextContent();
                        Number number = format.parse(value);
                        double d = number.doubleValue();//todo: BigDecimal

                        String charCode = ((Element) node).getElementsByTagName("CharCode").item(0).getTextContent();
                        double rate = d / nominal;

                        Currency currency = new Currency(charCode, rate);
                        currencyList.add(currency);

                        getContentResolver().insert(ReaderContentProvider.CURRENCIES_URI, currency.toContentValues());
                    }

                    System.out.println("currencyList = " + currencyList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
