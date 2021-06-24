package com.murgray.savehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class addItem extends AppCompatActivity {

    private EditText edtName, edtBrand, edtPrice, edtType;
    private Button saveBtn;
    private String[] bedroomItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Resources res = getResources();

        edtName = findViewById(R.id.edtName);
        edtBrand = findViewById(R.id.edtBrand);
        edtPrice = findViewById(R.id.edtPrice);
        edtType = findViewById(R.id.edtType);
        bedroomItems = res.getStringArray(R.array.bedroomItems);

        // get our button by its ID
        saveBtn = findViewById(R.id.saveBtn);

        // set its click listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(), "Posted Successfully!",
                        Toast.LENGTH_LONG
                ).show();

                TableLayout tl = findViewById(R.id.table_layout);
                TableRow tblrw = new TableRow(addItem.this);
                tblrw.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView newName = new TextView(addItem.this);
                newName.setText(edtName.getText() + "      ");
                newName.setTextColor(Color.DKGRAY);
                tblrw.addView(newName);

                TextView newBrand = new TextView(addItem.this);
                newBrand.setText(edtBrand.getText() + "     ");
                newBrand.setTextColor(Color.DKGRAY);
                tblrw.addView(newBrand);

                TextView newPrice = new TextView(addItem.this);
                newPrice.setText(edtPrice.getText() + "     ");
                newPrice.setTextColor(Color.DKGRAY);
                tblrw.addView(newPrice);

                TextView newType = new TextView(addItem.this);
                newType.setText(edtType.getText() + "       ");
                newType.setTextColor(Color.DKGRAY);
                tblrw.addView(newType);


                tl.addView(tblrw, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                edtName.setText("");
                edtBrand.setText("");
                edtPrice.setText("");
                edtType.setText("");

            }
        });
    }
}



