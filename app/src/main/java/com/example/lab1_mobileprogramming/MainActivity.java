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
import java.io.FileOutputStream;
import java.io.IOException;

//public class MainActivity extends AppCompatActivity {
//
//    private Spinner shapeSpinner;
//    private EditText coordinatesInput;
//    private Button calculateButton;
//    private Button saveButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        shapeSpinner = findViewById(R.id.shapeSpinner);
//        coordinatesInput = findViewById(R.id.coordinatesInput);
//        calculateButton = findViewById(R.id.calculateButton);
//        saveButton = findViewById(R.id.saveButton);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.shapes_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shapeSpinner.setAdapter(adapter);
//
//        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                coordinatesInput.setText("");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        calculateButton.setOnClickListener(v -> calculateShape());
//
//        saveButton.setOnClickListener(v -> saveData());
//    }
//
//    private void calculateShape() {
//        String selectedShape = shapeSpinner.getSelectedItem().toString();
//        String coordinates = coordinatesInput.getText().toString();
//
//        try {
//            if (coordinates.isEmpty()) {
//                throw new IllegalArgumentException("Coordinates cannot be empty.");
//            }
//
//            // Split coordinates and validate
//            String[] points = coordinates.split(";");
//            if (points.length < 2) {
//                throw new IllegalArgumentException("At least two points are required.");
//            }
//
//            // Parse coordinates and create shape object
//            Shape shape = ShapeFactory.createShape(selectedShape, points);
//            double area = shape.calculateArea();
//            double perimeter = shape.calculatePerimeter();
//
//            // Show results
//            Toast.makeText(this, "Area: " + area + ", Perimeter: " + perimeter, Toast.LENGTH_LONG).show();
//
//            // Draw shape on canvas
//            drawingView.setShape(shape);
//            drawingView.invalidate();
//
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void saveData() {
//        String data = shapeSpinner.getSelectedItem().toString() + ": " + coordinatesInput.getText().toString();
//        try (FileOutputStream fos = openFileOutput("shapes_data.txt", Context.MODE_PRIVATE)) {
//            fos.write(data.getBytes());
//            Toast.makeText(this, "Data saved to file.", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//}
public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText coordinatesInput;
    private Button calculateButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSpinner = findViewById(R.id.shapeSpinner);
        coordinatesInput = findViewById(R.id.coordinatesInput);
        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shapeType = shapeSpinner.getSelectedItem().toString();
                String[] points = coordinatesInput.getText().toString().split(";");

                try {
                    Shape shape = ShapeFactory.createShape(shapeType, points);
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

                // Data to be saved
                String dataToSave = "Shape: " + shapeType + "\nCoordinates: " + coordinates + "\n\n";

                // Save to file
                try {
                    FileOutputStream fos = openFileOutput("data.txt", MODE_APPEND);
                    fos.write(dataToSave.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this, "Data saved to data.txt!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error saving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivity(intent);
    }

    graphButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String shapeType = shapeSpinner.getSelectedItem().toString();
            String coordinates = coordinatesInput.getText().toString();

            // Start GraphActivity and pass shapeType and coordinates
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            intent.putExtra("shapeType", shapeType);
            intent.putExtra("coordinates", coordinates);
            startActivity(intent);
        }
    });
}