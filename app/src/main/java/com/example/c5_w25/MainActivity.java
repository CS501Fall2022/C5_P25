package com.example.c5_w25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private AutoCompleteTextView edt;
    private DataManager dbManager;
    private ArrayAdapter<String> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DataManager(this);
        btn = (Button) findViewById(R.id.button);
        edt = (AutoCompleteTextView ) findViewById(R.id.editEmail);
        updateEmails();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt.getText().toString();
                if (email.trim().equals("")){
                    return;
                }

                Friend friend = dbManager.selectByEmail(email);
                if (friend != null){
                    Toast.makeText(MainActivity.this, friend.getFirstName() + " " + friend.getLastName(),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Email Not Found",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_edit:
                Intent updateIntent = new Intent( this, UpdateActivity.class );
                this.startActivity( updateIntent );
                return true;
            case R.id.action_remove:
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateEmails();
    }

    public void updateEmails(){
        emails = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dbManager.selectEmails());;
        edt.setThreshold(1);
        edt.setAdapter(emails);
    }
}