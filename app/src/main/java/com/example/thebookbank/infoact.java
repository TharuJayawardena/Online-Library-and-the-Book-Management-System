package com.example.thebookbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class infoact extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    ImageView imageView;

    TextView BookName;

    Button addtoBorrow;

    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoact);

        imageView = findViewById(R.id.imageViewInfo);
        BookName = findViewById(R.id.BNameinInfo);


        // addtoBorrow = findViewById(R.id.addtoBorrow);


        // setting the name of drink
        BookName.setText("Jane eyre");






          /*  addtoBorrow.setOnClickListener(v -> {
                Intent intent = new Intent(infoact.this, sumAct.class);
                startActivity(intent);
                SaveCart();
            });*/

        // Text view number 1 to add hyperlink
        TextView linkTextView = findViewById(R.id.textView8);

        // method to redirect to provided link
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        // method to change color of link
        linkTextView.setLinkTextColor(Color.BLUE);


    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        BookName.setText("");


    }
}
