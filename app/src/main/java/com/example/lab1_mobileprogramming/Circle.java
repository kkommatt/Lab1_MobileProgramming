package com.example.lab1_mobileprogramming;

public class Circle implements Shape {
    public double radius;
    public double centerX;
    public double centerY;
    public Circle(String[] points) {
        if (points.length != 1) {
            throw new IllegalArgumentException("A circle requires 1 point (center) and a radius.");
        }
        try {
            String[] coords = points[0].split(",");
            if (coords.length != 3) {
                throw new IllegalArgumentException("3 coordinates should be provided");
            }
            centerX = Double.parseDouble(coords[0]);
            centerY = Double.parseDouble(coords[1]);
            this.radius = Double.parseDouble(coords[2]);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public double calculateArea() {
        return Math.round(Math.PI * Math.pow(radius, 2) * 100.0) / 100.0;
    }

    @Override
    public double calculatePerimeter() {
        return Math.round(2 * Math.PI * radius * 100.0) / 100.0;
    }
}




