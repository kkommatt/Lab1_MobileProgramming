package com.example.lab1_mobileprogramming;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Get shape type and coordinates from the intent
        String shapeType = getIntent().getStringExtra("shapeType");
        String coordinates = getIntent().getStringExtra("coordinates");
        String[] points = coordinates.split(";");

        // Find the custom graph view
        CustomGraphView graphView = findViewById(R.id.graph_view);

        try {
            // Set shape data for the custom graph view
            graphView.setShapeData(shapeType, points);

        } catch (Exception e) {
            Toast.makeText(this, "Error drawing shape: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
