package com.example.android.hotels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class NewHotelForm extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    Button btn_save;
    EditText et_title, et_price, et_description;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_hotel);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Adds an up button to the toolbar
        getSupportActionBar().setTitle("Add new Hotel");


        et_title = findViewById(R.id.etTitle);
        et_price = findViewById(R.id.etPrice);
        et_description = findViewById(R.id.etDescription);

        et_title.addTextChangedListener(saveTextWatcher);
        et_description.addTextChangedListener(saveTextWatcher);
        et_price.addTextChangedListener(saveTextWatcher);

        btn_save = findViewById(R.id.btnSave);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //This retrieves data from preferences

        String prefTitle = sharedpreferences.getString("title", "");
        String prefPrice = sharedpreferences.getString("price", "");
        String prefDescription = sharedpreferences.getString("description", "");

        et_title.setText(prefTitle);
        et_price.setText(prefPrice);
        et_description.setText(prefDescription);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTitle = et_title.getText().toString();
                String newPrice = et_price.getText().toString();
                String newDescription = et_description.getText().toString();

                // This is setting values into preferences

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("title", newTitle);
                editor.putString("price", newPrice);
                editor.putString("description", newDescription);
                editor.apply();

                // Start intent

                Intent i = new Intent(v.getContext(), MainActivity.class);

                    i.putExtra("title", newTitle);
                    i.putExtra("price", newPrice);
                    i.putExtra("description", newDescription);

                    setResult(RESULT_OK, i);
                    finish();
            }
        });


    }
    // This method enables save button only if EditText fields are filled
    private TextWatcher saveTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String titleInput = et_title.getText().toString().trim();
            String descriptionInput = et_description.getText().toString().trim();
            String priceInput = et_price.getText().toString().trim();
            btn_save.setEnabled(!titleInput.isEmpty() && !descriptionInput.isEmpty() && !priceInput.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
