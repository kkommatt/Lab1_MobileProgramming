package com.example.lab1_mobileprogramming;

public class Ellipse implements Shape {
    private double semiMajorAxis;
    private double semiMinorAxis;

    public Ellipse(String[] points) {
        if (points.length != 1) {
            throw new IllegalArgumentException("An ellipse requires 1 point (center) and two axes.");
        }
        String[] coords = points[0].split(",");
        semiMajorAxis = Double.parseDouble(coords[0]); // Major axis
        semiMinorAxis = Double.parseDouble(coords[1]); // Minor axis
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getSemiMinorAxis() {
        return semiMinorAxis;
    }

    @Override
    public double calculateArea() {
        return Math.PI * semiMajorAxis * semiMinorAxis;
    }

    @Override
    public double calculatePerimeter() {
        return Math.PI * (3 * (semiMajorAxis + semiMinorAxis) -
                Math.sqrt((3 * semiMajorAxis + semiMinorAxis) * (semiMajorAxis + 3 * semiMinorAxis)));
    }
}


