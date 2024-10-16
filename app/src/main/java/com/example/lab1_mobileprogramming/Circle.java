package com.example.lab1_mobileprogramming;

public class Circle implements Shape {
    private double radius;

    public Circle(String[] points) {
        if (points.length != 1) {
            throw new IllegalArgumentException("A circle requires 1 point (center) and a radius.");
        }
        String[] coords = points[0].split(",");
        double centerX = Double.parseDouble(coords[0]);
        double centerY = Double.parseDouble(coords[1]);
        this.radius = Double.parseDouble(coords[2]); // Adjust input accordingly
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}




