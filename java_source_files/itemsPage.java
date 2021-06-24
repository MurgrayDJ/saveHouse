package com.murgray.savehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class itemsPage extends AppCompatActivity implements itemsViewAdapter.ItemClickListener {

    private RecyclerView.Adapter mAdapter;
    Context thisContext;
    RecyclerView itemRecycler;
    saveHouseObject allitema = new saveHouseObject();

    ArrayList<Integer> itemID;
    ArrayList<String> itemNames;
    ArrayList<String> brands;
    ArrayList<Double> prices;
    ArrayList<String> types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_page);

        // data to populate the RecyclerView with
        /*ArrayList<String> itemNames = new ArrayList<>();
        itemNames.add("Curved Flatscreen TV");
        itemNames.add("Bed Frame");
        itemNames.add("Sequin Dress");
        itemNames.add("Lenovo Laptop");

        ArrayList<Double> prices = new ArrayList<>();
        prices.add(600.00);
        prices.add(250.00);
        prices.add(1000.00);
        prices.add(450.50);*/

        // set up the RecyclerView
        Resources res = getResources();


        Button addItemBtn = findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(itemsPage.this, addItem.class));
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(itemsPage.this, singleItem.class));
        GetData dataRetriever = new GetData();
        dataRetriever.execute("");

    }
    private class GetData extends AsyncTask<String,String,String>{

        String msg = "";

        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        static final String DB_URL = "jdbc:mysql://" +
                databaseStrings.DATABASE_URL + "/" +
                databaseStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(itemsPage.this, "Please wait...", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        protected String doInBackground(String... strings) {
            Connection connector = null;
            Statement stmt = null;

            try{
                Class.forName(JDBC_DRIVER).newInstance();
                connector = DriverManager.getConnection(DB_URL, databaseStrings.USERNAME, databaseStrings.PASSWORD);

                stmt = connector.createStatement();
                String sql = "SELECT * FROM itemsTable";

                ResultSet rs = stmt.executeQuery(sql);
                int itemIDN = rs.getInt("itemID");
                String itemNameN = rs.getString("itemsName");
                String brandN = rs.getString("Brand");
                Double priceN = rs.getDouble("Price");
                String typeN = rs.getString("Type");

                while(rs.next()){
                    itemID.add(itemIDN);
                    itemNames.add(itemNameN);
                    brands.add(brandN);
                    prices.add(priceN);
                    types.add(typeN);
                }
                saveHouseObject allItems = new saveHouseObject(itemID, itemNames,brands,prices,types);
                msg = "Process was successful.";
                rs.close();
                stmt.close();
                connector.close();

            } catch (SQLException connError){
                msg = "An exception error was thrown for JDBC";
                connError.printStackTrace();
            } catch (ClassNotFoundException e) {
                msg = "Driver Class not found exception was thrown";
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(stmt != null){
                        stmt.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                try{
                    if(connector != null){
                        connector.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(String msg){
            //progTextView.setText(this.msg);
            RecyclerView recyclerView = findViewById(R.id.ItemsTableRow);
            recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));
            mAdapter = new itemsViewAdapter(thisContext, itemID, itemNames, brands, prices, types);
            ((itemsViewAdapter) mAdapter).setClickListener(itemsPage.this::onItemClick);
            recyclerView.setAdapter(mAdapter);

        }
    }

}// End of outer class

