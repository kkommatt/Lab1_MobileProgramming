package com.example.lab1_mobileprogramming;

public class Triangle implements Shape {
    private double[] x, y;

    public Triangle(String[] points) {
        if (points.length != 3) {
            throw new IllegalArgumentException("A triangle requires 3 points.");
        }
        x = new double[3];
        y = new double[3];
        for (int i = 0; i < 3; i++) {
            String[] coords = points[i].split(",");
            x[i] = Double.parseDouble(coords[0]);
            y[i] = Double.parseDouble(coords[1]);
        }
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    @Override
    public double calculateArea() {
        double value = Math.abs((double) (x[0] * (y[1] - y[2]) + x[1] * (y[2] - y[0]) + x[2] * (y[0] - y[1]))) / 2.0;
        return value;
    }

    @Override
    public double calculatePerimeter() {
        return distance(x[0], y[0], x[1], y[1]) +
                distance(x[1], y[1], x[2], y[2]) +
                distance(x[2], y[2], x[0], y[0]);
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}



