package com.example.pixelinterpolation.interpolator.strategy;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class ProgressiveInterpolationStrategy {

    protected int width;
    protected int height;

    public abstract WritableImage interpolate(Image image1, Image image2, int step, int bandWidth);

    protected Color interpolateColor(Color colorA, Color colorB, double fraction) {
        double red = colorA.getRed() + fraction * (colorB.getRed() - colorA.getRed());
        double green = colorA.getGreen() + fraction * (colorB.getGreen() - colorA.getGreen());
        double blue = colorA.getBlue() + fraction * (colorB.getBlue() - colorA.getBlue());
        double opacity = colorA.getOpacity() + fraction * (colorB.getOpacity() - colorA.getOpacity());
        return new Color(red, green, blue, opacity);
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
}