package com.example.c5_w25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DataManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DataManager(this);
        updateView();
    }

    public void updateView(){
        ArrayList<Friend> friends = dbManager.selectAll();
        if (friends.size() >0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(friends.size());
            gridLayout.setColumnCount(5);

            TextView[] ids = new TextView[friends.size( )];
            EditText[][] namesAndEmails = new EditText[friends.size( )][3];
            Button[] buttons = new Button[friends.size( )];
            ButtonHandler bh = new ButtonHandler();


            Point size = new Point( );
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;
            for (Friend friend: friends){
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());

                namesAndEmails[i][0] = new EditText(this);
                namesAndEmails[i][1] = new EditText(this);
                namesAndEmails[i][2] = new EditText(this);
                namesAndEmails[i][0].setText(friend.getFirstName());
                namesAndEmails[i][1].setText(friend.getLastName());
                namesAndEmails[i][2].setText(friend.getEmail());

                namesAndEmails[i][0].setId(friend.getId()*10);
                namesAndEmails[i][1].setId(friend.getId()*10 + 1);
                namesAndEmails[i][2].setId(friend.getId()*10+2);

                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friend.getId( ) );
                buttons[i].setOnClickListener( bh );

                gridLayout.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.addView( namesAndEmails[i][0], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.addView( namesAndEmails[i][1], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.addView( namesAndEmails[i][2], ( int ) ( width * .3 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.addView( buttons[i], ( int ) ( width * .3 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }

            scrollView.addView( gridLayout );
            setContentView( scrollView );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            int friendId = v.getId( );
            EditText firstET = ( EditText ) findViewById( 10 * friendId );
            EditText lastET = ( EditText ) findViewById( 10 * friendId + 1 );
            EditText emailET = ( EditText) findViewById(10*friendId+2);
            String first = firstET.getText( ).toString( );
            String last = lastET.getText( ).toString( );
            String email = emailET.getText( ).toString( );

            dbManager.updateById( friendId, first, last, email );
            Toast.makeText( UpdateActivity.this, "Friend updated", Toast.LENGTH_SHORT ).show( );

            // update screen
            updateView( );
        }
    }
}