import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText coordinatesInput;
    private Button calculateButton;
    private Button saveButton;
    private MyCustomView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSpinner = findViewById(R.id.shapeSpinner);
        coordinatesInput = findViewById(R.id.coordinatesInput);
        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);
        drawingView = findViewById(R.id.drawingView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shapes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coordinatesInput.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        calculateButton.setOnClickListener(v -> calculateShape());

        saveButton.setOnClickListener(v -> saveData());
    }

    private void calculateShape() {
        String selectedShape = shapeSpinner.getSelectedItem().toString();
        String coordinates = coordinatesInput.getText().toString();

        try {
            if (coordinates.isEmpty()) {
                throw new IllegalArgumentException("Coordinates cannot be empty.");
            }

            // Split coordinates and validate
            String[] points = coordinates.split(";");
            if (points.length < 2) {
                throw new IllegalArgumentException("At least two points are required.");
            }

            // Parse coordinates and create shape object
            Shape shape = ShapeFactory.createShape(selectedShape, points);
            double area = shape.calculateArea();
            double perimeter = shape.calculatePerimeter();

            // Show results
            Toast.makeText(this, "Area: " + area + ", Perimeter: " + perimeter, Toast.LENGTH_LONG).show();

            // Draw shape on canvas
            drawingView.setShape(shape);
            drawingView.invalidate();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData() {
        String data = shapeSpinner.getSelectedItem().toString() + ": " + coordinatesInput.getText().toString();
        try (FileOutputStream fos = openFileOutput("shapes_data.txt", Context.MODE_PRIVATE)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Data saved to file.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}