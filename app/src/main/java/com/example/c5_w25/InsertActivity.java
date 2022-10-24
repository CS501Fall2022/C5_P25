package com.example.c5_w25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private DataManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DataManager( this );
        setContentView(R.layout.activity_insert);
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText firstEditText = ( EditText ) findViewById( R.id.input_first );
        EditText lastEditText = ( EditText ) findViewById( R.id.input_last );
        EditText emailEditText = ( EditText ) findViewById( R.id.input_last );
        String first = firstEditText.getText( ).toString( );
        String last = lastEditText.getText( ).toString( );
        String email = emailEditText.getText().toString();

        // insert new candy in database
            Friend friend = new Friend( 0, first, last, email );
            dbManager.insert( friend );
            Toast.makeText( this, "Friend added", Toast.LENGTH_SHORT ).show( );


        // clear data
        firstEditText.setText( "" );
        lastEditText.setText( "" );
        emailEditText.setText("");
    }

    public void close(View view){
        this.finish();
    }
}