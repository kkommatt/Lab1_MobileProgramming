package com.example.lab1_mobileprogramming;

public class ShapeFactory {
    public static Shape createShape(String shapeType, String[] points) {
        switch (shapeType) {
            case "Triangle":
                return new Triangle(points);
            case "Quadrilateral":
                return new Quadrilateral(points);
            case "Circle":
                return new Circle(points);
            case "Ellipse":
                return new Ellipse(points);
            default:
                throw new IllegalArgumentException("Unknown shape type.");
        }
    }
}
