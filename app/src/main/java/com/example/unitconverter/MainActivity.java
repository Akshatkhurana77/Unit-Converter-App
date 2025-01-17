package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertButton;
    TextView outputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertButton = findViewById(R.id.convertButton);
        outputValue = findViewById(R.id.outputValue);

        // Define unit options
        String[] units = {"Meters", "Kilometers", "Centimeters", "Inches", "Feet"};

        // Set up Spinners with unit options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String valueString = inputValue.getText().toString();
        if (valueString.isEmpty()) {
            outputValue.setText("Please enter a value");
            return;
        }

        double input = Double.parseDouble(valueString);
        String fromUnitSelected = fromUnit.getSelectedItem().toString();
        String toUnitSelected = toUnit.getSelectedItem().toString();
        double result = 0;

        // Conversion logic for Meters, Kilometers, Centimeters, Inches, and Feet
        if (fromUnitSelected.equals("Meters")) {
            if (toUnitSelected.equals("Kilometers")) {
                result = input / 1000;
            } else if (toUnitSelected.equals("Centimeters")) {
                result = input * 100;
            } else if (toUnitSelected.equals("Inches")) {
                result = input * 39.37;
            } else if (toUnitSelected.equals("Feet")) {
                result = input * 3.281;
            }
        } else if (fromUnitSelected.equals("Kilometers")) {
            if (toUnitSelected.equals("Meters")) {
                result = input * 1000;
            } else if (toUnitSelected.equals("Centimeters")) {
                result = input * 100000;
            } else if (toUnitSelected.equals("Inches")) {
                result = input * 39370.08;
            } else if (toUnitSelected.equals("Feet")) {
                result = input * 3280.84;
            }
        } else if (fromUnitSelected.equals("Centimeters")) {
            if (toUnitSelected.equals("Meters")) {
                result = input / 100;
            } else if (toUnitSelected.equals("Kilometers")) {
                result = input / 100000;
            } else if (toUnitSelected.equals("Inches")) {
                result = input / 2.54;
            } else if (toUnitSelected.equals("Feet")) {
                result = input / 30.48;
            }
        } else if (fromUnitSelected.equals("Inches")) {
            if (toUnitSelected.equals("Meters")) {
                result = input / 39.37;
            } else if (toUnitSelected.equals("Kilometers")) {
                result = input / 39370.08;
            } else if (toUnitSelected.equals("Centimeters")) {
                result = input * 2.54;
            } else if (toUnitSelected.equals("Feet")) {
                result = input / 12;
            }
        } else if (fromUnitSelected.equals("Feet")) {
            if (toUnitSelected.equals("Meters")) {
                result = input / 3.281;
            } else if (toUnitSelected.equals("Kilometers")) {
                result = input / 3280.84;
            } else if (toUnitSelected.equals("Centimeters")) {
                result = input * 30.48;
            } else if (toUnitSelected.equals("Inches")) {
                result = input * 12;
            }
        }

        // Display the result
        outputValue.setText("Converted value: " + result);
    }
}
