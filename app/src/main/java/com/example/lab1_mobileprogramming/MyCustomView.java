package com.example.lab1_mobileprogramming;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {
    private Shape shape;
    private Paint paint = new Paint();

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF000000); // Black color
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shape != null) {
            if (shape instanceof Triangle) {
                drawTriangle(canvas, (Triangle) shape);
            } else if (shape instanceof Quadrilateral) {
                drawQuadrilateral(canvas, (Quadrilateral) shape);
            } else if (shape instanceof Circle) {
                drawCircle(canvas, (Circle) shape);
            } else if (shape instanceof Ellipse) {
                drawEllipse(canvas, (Ellipse) shape);
            }
        }
    }

    private void drawTriangle(Canvas canvas, Triangle triangle) {
        Path path = new Path();
        path.moveTo((float) triangle.getX()[0], (float) triangle.getY()[0]);
        path.lineTo((float) triangle.getX()[1], (float) triangle.getY()[1]);
        path.lineTo((float) triangle.getX()[2], (float) triangle.getY()[2]);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawQuadrilateral(Canvas canvas, Quadrilateral quadrilateral) {
        Path path = new Path();
        path.moveTo((float) quadrilateral.getX()[0], (float) quadrilateral.getY()[0]);
        path.lineTo((float) quadrilateral.getX()[1], (float) quadrilateral.getY()[1]);
        path.lineTo((float) quadrilateral.getX()[2], (float) quadrilateral.getY()[2]);
        path.lineTo((float) quadrilateral.getX()[3], (float) quadrilateral.getY()[3]);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawCircle(Canvas canvas, Circle circle) {
        float radius = (float) circle.getRadius();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }

    private void drawEllipse(Canvas canvas, Ellipse ellipse) {
        float semiMajorAxis = (float) ellipse.getSemiMajorAxis();
        float semiMinorAxis = (float) ellipse.getSemiMinorAxis();
        canvas.drawOval(getWidth() / 2 - semiMajorAxis, getHeight() / 2 - semiMinorAxis,
                getWidth() / 2 + semiMajorAxis, getHeight() / 2 + semiMinorAxis, paint);
    }
}




