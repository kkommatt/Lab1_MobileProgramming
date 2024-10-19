package com.example.lab1_mobileprogramming;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import com.softmoore.android.graphlib.Point;

import androidx.appcompat.app.AppCompatActivity;

public class GraphActivity extends AppCompatActivity {

    private String shapeType;
    private String[] points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        try {
            shapeType = getIntent().getStringExtra("shapeType");
            String coordinates = getIntent().getStringExtra("coordinates");
            points = coordinates.split(";");

            if (shapeType == null || points.length == 0) {
                throw new IllegalArgumentException("Error in arguments");
            }

            switch (shapeType) {
                case "Circle":
                    drawCircle();
                    break;
                case "Ellipse":
                    drawEllipse();
                    break;
                case "Triangle":
                    drawTriangle();
                    break;
                case "Quadrilateral":
                    drawQuadrilateral();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error drawing shape: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void drawCircle() {
        Circle circle = new Circle(points);
        float radius = (float) circle.radius;
        double centerX = circle.centerX;
        double centerY = circle.centerY;
        Graph graph = new Graph.Builder()

                .addFunction(x -> Math.sqrt(Math.pow(radius, 2) - Math.pow(x - centerX, 2)) + centerY, Color.RED)
                .addFunction(x -> -Math.sqrt(Math.pow(radius, 2) - Math.pow(x - centerX, 2)) + centerY, Color.RED)
                .setWorldCoordinates(-10, 10, -20, 20)
                .build();
        GraphView graphView = findViewById(R.id.graph_view);
        graphView.setGraph(graph);
    }

    private void drawEllipse() {
        Ellipse ellipse = new Ellipse(points);
        double centerX = ellipse.x;
        double centerY = ellipse.y;
        double semiMajor = ellipse.semiMajorAxis;
        double semiMinor = ellipse.semiMinorAxis;

        Graph graph = new Graph.Builder()
                .addFunction(x -> centerY + semiMinor * Math.sqrt(1 - Math.pow((x - centerX) / semiMajor, 2)), Color.RED)
                .addFunction(x -> centerY - semiMinor * Math.sqrt(1 - Math.pow((x - centerX) / semiMajor, 2)), Color.RED)
                .setWorldCoordinates(-10, 10, -20, 20)
                .build();

        GraphView graphView = findViewById(R.id.graph_view);
        graphView.setGraph(graph);
    }

    private void drawTriangle() {
        Triangle triangle = new Triangle(points);


        double x1 = triangle.x[0];
        double y1 = triangle.y[0];
        double x2 = triangle.x[1];
        double y2 = triangle.y[1];
        double x3 = triangle.x[2];
        double y3 = triangle.y[2];


        double m1 = (y2 - y1) / (x2 - x1);
        double b1 = y1 - m1 * x1;

        double m2 = (y3 - y2) / (x3 - x2);
        double b2 = y2 - m2 * x2;

        double m3 = (y1 - y3) / (x1 - x3);
        double b3 = y3 - m3 * x3;

        Graph graph = new Graph.Builder()
                .addFunction(x -> (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)) ? m1 * x + b1 : 1000, Color.RED)
                .addFunction(x -> (x >= Math.min(x2, x3) && x <= Math.max(x2, x3)) ? m2 * x + b2 : 1000, Color.RED)
                .addFunction(x -> (x >= Math.min(x1, x3) && x <= Math.max(x1, x3)) ? m3 * x + b3 : 1000, Color.RED)
                .setWorldCoordinates(-10, 10, -20, 20)
                .build();


        GraphView graphView = findViewById(R.id.graph_view);
        graphView.setGraph(graph);
    }

    private void drawQuadrilateral() {
        Quadrilateral quadrilateral = new Quadrilateral(points);
        double x1 = quadrilateral.x[0];
        double y1 = quadrilateral.y[0];
        double x2 = quadrilateral.x[1];
        double y2 = quadrilateral.y[1];
        double x3 = quadrilateral.x[2];
        double y3 = quadrilateral.y[2];
        double x4 = quadrilateral.x[3];
        double y4 = quadrilateral.y[3];

        double m1 = (y2 - y1) / (x2 - x1);
        double b1 = y1 - m1 * x1;

        double m2 = (y3 - y2) / (x3 - x2);
        double b2 = y2 - m2 * x2;

        double m3 = (y4 - y3) / (x4 - x3);
        double b3 = y3 - m3 * x3;

        double m4 = (y1 - y4) / (x1 - x4);
        double b4 = y4 - m4 * x4;

        Graph graph = new Graph.Builder()
                .addFunction(x -> (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)) ? m1 * x + b1 : 1000, Color.RED)
                .addFunction(x -> (x >= Math.min(x2, x3) && x <= Math.max(x2, x3)) ? m2 * x + b2 : 1000, Color.RED)
                .addFunction(x -> (x >= Math.min(x4, x3) && x <= Math.max(x4, x3)) ? m3 * x + b3 : 1000, Color.RED)
                .addFunction(x -> (x >= Math.min(x4, x1) && x <= Math.max(x4, x1)) ? m4 * x + b4 : 1000, Color.RED)
                .setWorldCoordinates(-10, 10, -20, 20)
                .build();

        GraphView graphView = findViewById(R.id.graph_view);
        graphView.setGraph(graph);

    }

    public void openMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}