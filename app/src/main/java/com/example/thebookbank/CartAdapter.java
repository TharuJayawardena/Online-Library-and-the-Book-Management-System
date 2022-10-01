package com.example.thebookbank;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.thebookbank.OrderContract;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {



        TextView BookName, price, quantity;


        BookName = view.findViewById(R.id.BookNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);

        quantity = view.findViewById(R.id.quantityinOrderSummary);

        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofBook = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofBook = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);



        String nameofBook = cursor.getString(name);
        String pricesofBook = cursor.getString(priceofBook);
        String quantitysofBook = cursor.getString(quantityofBook);




        BookName.setText(nameofBook);
        price.setText(pricesofBook);
        quantity.setText(quantitysofBook);

    }
}


