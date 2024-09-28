package com.example.pixelinterpolation.formatter;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class TextFieldFormatter {

    public static void setupTextFieldFormatter(TextField textField, int minValue, int maxValue) {
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), minValue, change -> {
            String newText = change.getControlNewText();

            if (!newText.isEmpty()) {
                try {
                    int value = Integer.parseInt(newText);
                    if (value < minValue || value > maxValue) {
                        return null;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return change;
        });

        textField.setTextFormatter(formatter);
    }
}