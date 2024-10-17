package com.example.lab1_mobileprogramming;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;
import com.softmoore.android.graphlib.Point;

import java.io.FileInputStream;
import java.io.IOException;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        String shapeType = getIntent().getStringExtra("shapeType");
        String coordinates = getIntent().getStringExtra("coordinates");
        String[] points = coordinates.split(";");

        GraphView graphView = findViewById(R.id.graph_view);

        try {
            drawShape(graphView, shapeType, points);
        } catch (Exception e) {
            Toast.makeText(this, "Error drawing shape: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void drawShape(GraphView graphView, String shapeType, String[] points) {
        graphView.setOnDrawListener(new GraphView.OnDrawListener() {
            @Override
            public void onDraw(Canvas canvas) {
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(5);
                paint.setStyle(Paint.Style.STROKE);

                switch (shapeType) {
                    case "Circle":
                        Circle circle = new Circle(points);
                        float radius = (float) circle.getRadius();
                        canvas.drawCircle(500, 500, radius, paint);  // Center at (500, 500)
                        break;

                    case "Ellipse":
                        Ellipse ellipse = new Ellipse(points);
                        float semiMajor = (float) ellipse.getSemiMajorAxis();
                        float semiMinor = (float) ellipse.getSemiMinorAxis();
                        canvas.drawOval(300, 400, 300 + semiMajor * 2, 400 + semiMinor * 2, paint);  // Example bounds
                        break;

                    case "Triangle":
                        Triangle triangle = new Triangle(points);
                        float[] trianglePoints = getPolygonPoints(triangle.getX(), triangle.getY());
                        canvas.drawLines(trianglePoints, paint);
                        break;

                    case "Quadrilateral":
                        Quadrilateral quadrilateral = new Quadrilateral(points);
                        float[] quadPoints = getPolygonPoints(quadrilateral.getX(), quadrilateral.getY());
                        canvas.drawLines(quadPoints, paint);
                        break;

                    default:
                        throw new IllegalArgumentException("Unknown shape type: " + shapeType);
                }
            }
        });
    }

    private float[] getPolygonPoints(double[] x, double[] y) {
        float[] points = new float[x.length * 4];  // Each pair of points needs 4 values (x1, y1, x2, y2)
        for (int i = 0; i < x.length; i++) {
            int next = (i + 1) % x.length;  // To close the shape, connect the last point to the first
            points[i * 4] = (float) x[i];
            points[i * 4 + 1] = (float) y[i];
            points[i * 4 + 2] = (float) x[next];
            points[i * 4 + 3] = (float) y[next];
        }
        return points;
    }
}