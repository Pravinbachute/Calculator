package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        Spinner unitTypeSpinner = findViewById(R.id.unitTypeSpinner);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        Button convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Set up unit types
        ArrayAdapter<CharSequence> unitTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.unit_types, android.R.layout.simple_spinner_item);
        unitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(unitTypeAdapter);

        // Set up unit selections based on unit type
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int arrayId;
                switch (position) {
                    case 0: // Length
                        arrayId = R.array.length_units;
                        break;
                    case 1: // Weight
                        arrayId = R.array.weight_units;
                        break;
                    default:
                        arrayId = R.array.length_units;
                }
                ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        arrayId, android.R.layout.simple_spinner_item);
                unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fromUnitSpinner.setAdapter(unitAdapter);
                toUnitSpinner.setAdapter(unitAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set up conversion button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input = Double.parseDouble(inputValue.getText().toString());
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();
                double result = convertUnits(input, fromUnit, toUnit);
                resultTextView.setText(String.valueOf(result));
            }
        });
    }

    private double convertUnits(double input, String fromUnit, String toUnit) {
        // Conversion logic, simplified example for length
        if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
            return input / 100;
        } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
            return input * 100;
        }
        // Add more conversion logic here
        return input; // Default: no conversion
    }
}
