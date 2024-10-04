package com.example.pixelinterpolation.interpolator;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class StandardInterpolator extends AbstractInterpolator {

    private int steps = 100;
    private int delay = 0;

    public StandardInterpolator(ImageView imageViewA, ImageView imageViewB, Image image1, Image image2) {
        super(imageViewA, imageViewB, image1, image2);
    }

    public void startInterpolation(Runnable onComplete) {
        PixelReader reader1 = image1.getPixelReader();
        PixelReader reader2 = image2.getPixelReader();
        WritableImage blendedImage1 = new WritableImage(width, height);
        WritableImage blendedImage2 = new WritableImage(width, height);
        PixelWriter writer1 = blendedImage1.getPixelWriter();
        PixelWriter writer2 = blendedImage2.getPixelWriter();

        callback.onInterpolationStarted();

        animationTimer = new AnimationTimer() {
            private int step = 0;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (isStopped) {
                    stop();
                    onComplete.run();
                    callback.onInterpolationFinished();
                    return;
                }

                if (now - lastUpdate >= delay * 1_000_000) {
                    if (step >= steps) {
                        stop();
                        onComplete.run();
                        callback.onInterpolationFinished();
                        return;
                    }

                    interpolate(reader1, reader2, writer1, writer2, step);

                    imageViewA.setImage(blendedImage1);
                    imageViewB.setImage(blendedImage2);

                    step++;
                    lastUpdate = now;
                }
            }
        };

        isStopped = false;
        animationTimer.start();
    }

    private void interpolate(PixelReader reader1, PixelReader reader2, PixelWriter writer1, PixelWriter writer2, int step) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color1 = reader1.getColor(x, y);
                Color color2 = reader2.getColor(x, y);

                double red1 = interpolate(color1.getRed(), color2.getRed(), step);
                double green1 = interpolate(color1.getGreen(), color2.getGreen(), step);
                double blue1 = interpolate(color1.getBlue(), color2.getBlue(), step);

                double red2 = interpolate(color2.getRed(), color1.getRed(), step);
                double green2 = interpolate(color2.getGreen(), color1.getGreen(), step);
                double blue2 = interpolate(color2.getBlue(), color1.getBlue(), step);

                writer1.setColor(x, y, Color.color(red1, green1, blue1));
                writer2.setColor(x, y, Color.color(red2, green2, blue2));
            }
        }
    }

    private double interpolate(double value1, double value2, int step) {
        return value1 + (value2 - value1) * step / steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}