package com.murgray.savehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class userPage extends AppCompatActivity {

    ListView roomsListView;
    String[] roomsStringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Resources res = getResources();
        roomsListView = findViewById(R.id.roomsListView);
        roomsStringList = res.getStringArray(R.array.roomsStringList);

        roomsListView.setAdapter(new ArrayAdapter<String>(this, R.layout.my_roomslist_details, roomsStringList));

        roomsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showRoomPage = new Intent(getApplicationContext(), roomPage.class);
                showRoomPage.putExtra("com.murgray.savehouse.ITEM_INDEX", position);
                String roomTitle = (String)parent.getItemAtPosition(position);
                showRoomPage.putExtra("com.murgray.savehouse.ROOM_TITLE", roomTitle);

                startActivity(showRoomPage);
            }
        });

    }
}
