package com.example.lab1_mobileprogramming;

public class Quadrilateral implements Shape {
    public double[] x, y;

    public Quadrilateral(String[] points) {
        if (points.length != 4) {
            throw new IllegalArgumentException("A quadrilateral requires 4 points.");
        }
        try {
            x = new double[4];
            y = new double[4];
            for (int i = 0; i < 4; i++) {
                String[] coords = points[i].split(",");
                x[i] = Double.parseDouble(coords[0]);
                y[i] = Double.parseDouble(coords[1]);
            }
        } catch (Exception e)
        {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public double calculateArea() {
        return Math.round(Math.abs(((x[0] * y[1] + x[1] * y[2] + x[2] * y[3] + x[3] * y[0]) -
                (y[0] * x[1] + y[1] * x[2] + y[2] * x[3] + y[3] * x[0])) / 2.0) * 100.0) / 100.0;
    }

    @Override
    public double calculatePerimeter() {
        return Math.round((distance(x[0], y[0], x[1], y[1]) +
                distance(x[1], y[1], x[2], y[2]) +
                distance(x[2], y[2], x[3], y[3]) +
                distance(x[3], y[3], x[0], y[0])) * 100.0) / 100.0;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}





