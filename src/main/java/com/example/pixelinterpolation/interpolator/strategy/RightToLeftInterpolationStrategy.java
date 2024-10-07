package com.example.pixelinterpolation.interpolator.strategy;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class RightToLeftInterpolationStrategy extends DirectionalInterpolationStrategy {

    @Override
    public WritableImage interpolate(Image image1, Image image2, int step, int bandWidth) {
        PixelReader readerA = image1.getPixelReader();
        PixelReader readerB = image2.getPixelReader();
        WritableImage resultImage = new WritableImage(width, height);
        PixelWriter writer = resultImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                int value = step - ((width - 1 - x) / (bandWidth / COLOR_INTERPOLATION_STEPS));
                value = Math.max(0, Math.min(COLOR_INTERPOLATION_STEPS, value));

                Color colorA = readerA.getColor(x, y);
                Color colorB = readerB.getColor(x, y);
                Color interpolatedColor = interpolateColor(colorA, colorB, value / (double) COLOR_INTERPOLATION_STEPS);

                writer.setColor(x, y, interpolatedColor);
            }
        }

        return resultImage;
    }
}