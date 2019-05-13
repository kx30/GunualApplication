package ru.webant.nikolay.gunual.filter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import ru.webant.gunual.R;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    private String country;
    private String ammo;
    private ArrayList<String> countries;
    private ArrayList<String> ammunition;
    private Button acceptButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initToolbar();

        Bundle arguments = getIntent().getExtras();
        countries = arguments.getStringArrayList("countries");
        ammunition = arguments.getStringArrayList("ammo");
        Collections.sort(countries);
        Collections.sort(ammunition);
        countries.add(0, "Country");
        ammunition.add(0, "Ammo");

        Spinner countrySpinner = findViewById(R.id.country_spinner);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = countries.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner ammoSpinner = findViewById(R.id.ammunition_spinner);
        ArrayAdapter<String> ammunitionAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, ammunition);
        ammunitionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ammoSpinner.setAdapter(ammunitionAdapter);

        ammoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ammo = ammunition.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        acceptButton = findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accept_button:
                Intent data = new Intent();
                if (country.equals("Country") && ammo.equals("Ammo")) {
                    setResult(RESULT_CANCELED);
                    finish();
                } else if (country.equals("Country")) {
                    data.putExtra("ammo", ammo);
                    setResult(RESULT_OK, data);
                    finish();
                } else if (ammo.equals("Ammo")) {
                    data.putExtra("country", country);
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    data.putExtra("country", country);
                    data.putExtra("ammo", ammo);
                    setResult(RESULT_OK, data);
                    finish();
                }

            case R.id.cancel_button:
                setResult(RESULT_CANCELED);
                finish();
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }
}
