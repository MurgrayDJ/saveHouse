package com.murgray.savehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;

public class roomPage extends AppCompatActivity {

    String[] bedroomItems;
    String[] prices;
    String[] brands;
    private PlaceHolderView mGalleryView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_page);

        Button viewItemsBtn = findViewById(R.id.viewItemsBtn);
        viewItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(roomPage.this, itemsPage.class));
            }
        });

        Resources res = getResources();
        bedroomItems = res.getStringArray(R.array.bedroomItems);
        prices = res.getStringArray(R.array.prices);
        brands = res.getStringArray(R.array.brands);

        Intent in = getIntent();
        int index = in.getIntExtra("com.murgray.savehouse.ITEM_INDEX", -1);

        if(getIntent().hasExtra("com.murgray.savehouse.ROOM_TITLE")){
            TextView pageTitleFromList = findViewById(R.id.roomTitle);
            String text = getIntent().getExtras().getString("com.murgray.savehouse.ROOM_TITLE");
            pageTitleFromList.setText(text);

        }

        if (index > -1) {
            int pic = getImg(index);
            ImageView img = findViewById(R.id.roomImageView);
            scaleImg(img, pic);
        }

        mGalleryView = findViewById(R.id.galleryView);
        mGalleryView
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.kitchen_pic)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.bedroom_1_pic)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.bathroom)));
    }

    private int getImg(int index){
        switch (index) {
            case 0: return R.drawable.bedroom_1_pic;
            case 3: return R.drawable.kitchen_pic;
            default: return R.drawable.placeholder;
        }
    }

    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth/(float)screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);

    }
}

