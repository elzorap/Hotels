package com.example.android.hotels;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class HotelsDetailes extends AppCompatActivity {

    ImageView image;
    TextView description;
    TextView price;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_detailes);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Adds an up button to the toolbar
        getSupportActionBar().setTitle("Hotel detailes");

        image = findViewById(R.id.ivHotel);
        description = findViewById(R.id.tvDescription);
        price = findViewById(R.id.tvPrice);

        Intent intent = getIntent();

        Hotel hotel = (Hotel) getIntent().getSerializableExtra("hotel");


        if (hotel != null) {
            description.setText(hotel.getDescription());
            price.setText(String.valueOf(hotel.getPrice()));
            image.setImageResource(hotel.getImage());

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}