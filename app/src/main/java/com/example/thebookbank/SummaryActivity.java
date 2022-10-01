package com.example.thebookbank;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thebookbank.OrderContract;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public CartAdapter mAdapter;
    public static final int LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        Button clearthedata = findViewById(R.id.clearthedatabase);
        Button payment = findViewById(R.id.pay);

        clearthedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm("");
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddtoDelivery.class);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(LOADER, null, this);

        ListView listView = findViewById(R.id.list);
        mAdapter = new CartAdapter(this, null);
        listView.setAdapter(mAdapter);



    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,

        };

        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);
    }

    private void confirm(String message){
        AlertDialog dlg = new AlertDialog.Builder(SummaryActivity.this)
                .setTitle("Delete Cart")
                .setMessage("Are you sure?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
                    }
                })
                .create();
        dlg.show();
    }
}