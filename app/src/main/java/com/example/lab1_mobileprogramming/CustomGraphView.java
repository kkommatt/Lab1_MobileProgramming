package com.example.lab1_mobileprogramming;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomGraphView extends View {

    private String shapeType;
    private String[] points;

    private Paint paint;

    public CustomGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    public void setShapeData(String shapeType, String[] points) {
        this.shapeType = shapeType;
        this.points = points;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (shapeType == null || points == null || points.length == 0) {
            return;
        }

        drawAxes(canvas);

        switch (shapeType) {
            case "Circle":
                drawCircle(canvas);
                break;
            case "Ellipse":
                drawEllipse(canvas);
                break;
            case "Triangle":
                drawTriangle(canvas);
                break;
            case "Quadrilateral":
                drawQuadrilateral(canvas);
                break;
            default:
                break;
        }
    }

    private void drawAxes(Canvas canvas) {
        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();

        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.GRAY);
        axisPaint.setStyle(Paint.Style.STROKE);
        axisPaint.setStrokeWidth(2);

        canvas.drawLine(0, canvasHeight / 2, canvasWidth, canvasHeight / 2, axisPaint);
        canvas.drawLine(canvasWidth / 2, 0, canvasWidth / 2, canvasHeight, axisPaint);
    }

    private void drawCircle(Canvas canvas) {
        float radius = Float.parseFloat(points[0]);
        canvas.drawCircle(500, 500, radius, paint);
    }

    private void drawEllipse(Canvas canvas) {
        float semiMajor = Float.parseFloat(points[0]);
        float semiMinor = Float.parseFloat(points[1]);
        canvas.drawOval(300, 400, 300 + semiMajor * 2, 400 + semiMinor * 2, paint);
    }

    private void drawTriangle(Canvas canvas) {
        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();

        float x1 = Float.parseFloat(points[0].split(",")[0]);
        float y1 = Float.parseFloat(points[0].split(",")[1]);
        float x2 = Float.parseFloat(points[1].split(",")[0]);
        float y2 = Float.parseFloat(points[1].split(",")[1]);
        float x3 = Float.parseFloat(points[2].split(",")[0]);
        float y3 = Float.parseFloat(points[2].split(",")[1]);

        float maxCoordinate = 100; // Set maximum coordinate value
        float scaleFactor = Math.min(canvasWidth, canvasHeight) / (maxCoordinate * 2);

        x1 *= scaleFactor;
        y1 *= scaleFactor;
        x2 *= scaleFactor;
        y2 *= scaleFactor;
        x3 *= scaleFactor;
        y3 *= scaleFactor;

        float offsetX = canvasWidth / 2;
        float offsetY = canvasHeight / 2;
        x1 += offsetX;
        y1 += offsetY;
        x2 += offsetX;
        y2 += offsetY;
        x3 += offsetX;
        y3 += offsetY;

        float[] trianglePoints = {
                x1, y1, x2, y2,
                x2, y2, x3, y3,
                x3, y3, x1, y1
        };

        canvas.drawLines(trianglePoints, paint);
    }

    private void drawQuadrilateral(Canvas canvas) {
        float[] quadPoints = parsePolygonPoints(points);
        canvas.drawLines(quadPoints, paint);
    }

    private float[] parsePolygonPoints(String[] points) {
        float[] coords = new float[points.length * 2];
        for (int i = 0; i < points.length; i++) {
            String[] xy = points[i].split(",");
            coords[i * 2] = Float.parseFloat(xy[0]);
            coords[i * 2 + 1] = Float.parseFloat(xy[1]);
        }
        return coords;
    }
}
