package com.example.lab1_mobileprogramming;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText coordinatesInput;
    private Button calculateButton;
    private Button saveButton;
    private Button graphButton;
    private Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSpinner = findViewById(R.id.shapeSpinner);
        coordinatesInput = findViewById(R.id.coordinatesInput);
        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);
        graphButton = findViewById(R.id.graphButton);
        readButton = findViewById(R.id.readButton);

        String[] shapes = {"Triangle", "Circle", "Ellipse", "Quadrilateral"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shapes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String shapeType = shapeSpinner.getSelectedItem().toString();
                    String[] points = coordinatesInput.getText().toString().split(";");
                    var shape = ShapeFactory.createShape(shapeType, points);
                    double area = shape.calculateArea();
                    double perimeter = shape.calculatePerimeter();
                    Toast.makeText(MainActivity.this,
                            "Area: " + area + ", Perimeter: " + perimeter,
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shapeType = shapeSpinner.getSelectedItem().toString();
                String coordinates = coordinatesInput.getText().toString();

                String dataToSave = "Shape: " + shapeType + "\nCoordinates: " + coordinates + "\n\n";

                try {
                    if (shapeType == null || coordinates == null || coordinates.isEmpty()) {
                        throw new IllegalArgumentException("Empty values");
                    }

                    String[] points_check = coordinatesInput.getText().toString().split(";");
                    var shape = ShapeFactory.createShape(shapeType, points_check);
                    FileOutputStream fos = openFileOutput("data.txt", MODE_PRIVATE);
                    fos.write(dataToSave.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this, "Data saved to data.txt!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error saving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("data.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    String lastShapeType = "";
                    String lastCoordinates = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                        if (line.startsWith("Shape: ")) {
                            lastShapeType = line.substring("Shape: ".length()).trim();
                        }
                        // Check if the line contains coordinates
                        if (line.startsWith("Coordinates: ")) {
                            lastCoordinates = line.substring("Coordinates: ".length()).trim();
                        }
                    }

                    fis.close();

                    if (!lastShapeType.isEmpty()) {
                        int spinnerPosition = ((ArrayAdapter<String>) shapeSpinner.getAdapter()).getPosition(lastShapeType);
                        if (spinnerPosition >= 0) {
                            shapeSpinner.setSelection(spinnerPosition);
                        }
                    }

                    coordinatesInput.setText(lastCoordinates);
                    Toast.makeText(MainActivity.this, "Data read from data.txt!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error showing graph: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String shapeType = shapeSpinner.getSelectedItem().toString();
                    String coordinates = coordinatesInput.getText().toString();

                    if (shapeType == null || coordinates == null || coordinates.isEmpty()) {
                        throw new IllegalArgumentException("Empty values");
                    }

                    String[] points_check = coordinatesInput.getText().toString().split(";");
                    var shape = ShapeFactory.createShape(shapeType, points_check);

                    Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                    intent.putExtra("shapeType", shapeType);
                    intent.putExtra("coordinates", coordinates);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error showing graph: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Button authorButton = findViewById(R.id.Author);
        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAuthorActivity();
            }
        });
    }

    public void openAuthorActivity() {
        try {
            Intent intent = new Intent(this, AuthorActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error showing author: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
