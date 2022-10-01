package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LatteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, BookName, BookPrice;

    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        BookName = findViewById(R.id.BookNameinInfo);
        BookPrice = findViewById(R.id.BookPrice);

        addtoCart = findViewById(R.id.addtocart);




        BookName.setText("Kite Runner");
        imageView.setImageResource(R.drawable.kite);

        addtoCart.setOnClickListener(v -> {
            Intent intent = new Intent(LatteActivity.this, SummaryActivity.class);
            startActivity(intent);
            SaveCart();
        });

        plusquantity.setOnClickListener(v -> {

            int basePrice = 420;
            quantity++;
            displayQuantity();
            int bookPrice = basePrice * quantity;
            String setnewPrice = String.valueOf(bookPrice);
            BookPrice.setText(setnewPrice);






        });

        minusquantity.setOnClickListener(v -> {

            int basePrice = 420;
            // because we dont want the quantity go less than 0
            if (quantity == 0) {
                Toast.makeText(LatteActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
            } else {
                quantity--;
                displayQuantity();
                int bookPrice = basePrice * quantity;
                String setnewPrice = String.valueOf(bookPrice);
                BookPrice.setText(setnewPrice);




            }
        });



    }

    private boolean SaveCart() {

        // getting the values from our views
        String name = BookName.getText().toString();
        String price = BookPrice.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);



        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }



    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,

        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);


            String nameofBook = cursor.getString(name);
            String priceofBook = cursor.getString(price);
            String quantityofBook = cursor.getString(quantity);


            BookName.setText(nameofBook);
            BookPrice.setText(priceofBook);
            quantitynumber.setText(quantityofBook);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        BookName.setText("");
        BookPrice.setText("");
        quantitynumber.setText("");

    }
}

