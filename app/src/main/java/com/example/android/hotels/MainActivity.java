package com.example.android.hotels;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ListView lv_hotels;
    HotelsAdapter adapter;
    ArrayList<Hotel> listOfHotels;
    Toolbar toolbar;
    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

         listOfHotels = new ArrayList<>();

        Hotel hotel1 = new Hotel();
        hotel1.setImage(R.drawable.hotel_parc_01);
        hotel1.setTitle("Hotel Parc");
        hotel1.setDescription("Hotel Parc, located in Mamaia, features rooms with balconies less than 328 feet from the beach. You can admire the sunset from the 14th-floor Sky View Bar.");
        hotel1.setPrice(110);
        listOfHotels.add(hotel1);

        Hotel hotel2 = new Hotel();
        hotel2.setImage(R.drawable.hotel_bavaria_blu_02);
        hotel2.setTitle("Hotel Bavaria Blu");
        hotel2.setDescription("Only 66 feet from the beach, the 4-star Bavaria Blu is located at the entrance to Mamaia Resort. It offers a seasonal outdoor pool from June through August, and free WiFi.");
        hotel2.setPrice(100);
        listOfHotels.add(hotel2);

        Hotel hotel3 = new Hotel();
        hotel3.setImage(R.drawable.hotel_ramada_03);
        hotel3.setTitle("Hotel Malibu");
        hotel3.setDescription("With its beach-front position, a swimming pool, a children´s playground and cool modern architecture, this property offers the ideal spot for exploring the surroundings.");
        hotel3.setPrice(90);
        listOfHotels.add(hotel3);

        Hotel hotel4 = new Hotel();
        hotel4.setImage(R.drawable.hotel_malibu_04);
        hotel4.setTitle("Hotel Alcor Beach");
        hotel4.setDescription("Ramada by Wyndham Constanta Hotel is located in Mamaia, on the shores of Lake Tabacaria, in the immediate vicinity of the Holiday Village, just a 5 minute walk from the beach, 1640 feet from the...\n");
        hotel4.setPrice(80);
        listOfHotels.add(hotel4);

        Hotel hotel5 = new Hotel();
        hotel5.setImage(R.drawable.hotel_alcor_beach_05);
        hotel5.setTitle("Hotel Ramada");
        hotel5.setDescription("Located at the beachfront of Mamaia resort, steps away from the Black Sea, this hotel offers rooms with balconies and private bathrooms. Rooms come with a TV, a seating area and a fridge.");
        hotel5.setPrice(70);
        listOfHotels.add(hotel5);

        // Adds listOfHotels in ListView using the adapter

        lv_hotels = findViewById(R.id.lvHotels);
        adapter = new HotelsAdapter(MainActivity.this, listOfHotels);
        lv_hotels.setAdapter(adapter);

        // Starts HotelsDetailes Activity

        lv_hotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), HotelsDetailes.class);
                intent.putExtra("hotel", listOfHotels.get(position));
                startActivity(intent);


            }
        });
    }

    // Creates the toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);

        return true;
    }

    // Add new hotel

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_button) {
            Intent i = new Intent(getApplicationContext(), NewHotelForm.class);
            startActivityForResult(i, 101);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE && data != null) {

                Hotel hotel = new Hotel();
                hotel.setTitle(data.getStringExtra("title"));
                hotel.setPrice(Integer.parseInt(Objects.requireNonNull(data.getStringExtra("price"))));
                hotel.setDescription(data.getStringExtra("description"));

                listOfHotels.add(hotel);
                adapter.notifyDataSetChanged();
            } else if (data == null){
                Toast.makeText(this, "Fill form data", Toast.LENGTH_SHORT).show();
            }
        }

    }
}