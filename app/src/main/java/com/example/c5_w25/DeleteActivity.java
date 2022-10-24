package com.example.c5_w25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
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
            gridLayout.setColumnCount(2);

            TextView[] strings = new TextView[friends.size()];
            Button[] buttons = new Button[friends.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);

            int width = size.x;
            int i = 0;

            for (Friend friend: friends){
                strings[i] = new TextView(this);
                buttons[i] = new Button(this);
                strings[i].setText(friend.toString());
                buttons[i].setText("Delete");
                buttons[i].setId(friend.getId());
                buttons[i].setOnClickListener(bh);

                gridLayout.addView( strings[i], (int) (width * .6),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.addView(buttons[i], (int) (width*.4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                 i++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }

    }

    private class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int friendId = view.getId();

            dbManager.deleteById(friendId);
            Toast.makeText(DeleteActivity.this, "Deleted Friend", Toast.LENGTH_LONG).show();

            updateView();
        }
    }
}