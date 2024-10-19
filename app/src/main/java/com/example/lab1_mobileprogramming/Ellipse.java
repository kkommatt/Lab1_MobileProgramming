package com.example.lab1_mobileprogramming;

public class Ellipse implements Shape {
    public double semiMajorAxis;
    public double semiMinorAxis;

    public double x;
    public double y;

    public Ellipse(String[] points) {
        if (points.length != 1) {
            throw new IllegalArgumentException("An ellipse requires 1 point (center) and two axes.");
        }
        try {
            String[] coords = points[0].split(",");
            if (coords.length != 4) {
                throw new IllegalArgumentException("4 values should be provided - x, y, a, b");
            }
            x = Double.parseDouble(coords[0]);
            y = Double.parseDouble(coords[1]);
            semiMajorAxis = Double.parseDouble(coords[2]);
            semiMinorAxis = Double.parseDouble(coords[3]);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public double calculateArea() {
        return Math.round(Math.PI * semiMajorAxis * semiMinorAxis * 100.0) / 100.0;
    }

    @Override
    public double calculatePerimeter() {
        return Math.round((Math.PI * (3 * (semiMajorAxis + semiMinorAxis) -
                Math.sqrt((3 * semiMajorAxis + semiMinorAxis) * (semiMajorAxis + 3 * semiMinorAxis)))) * 100.0) / 100.0;
    }
}


