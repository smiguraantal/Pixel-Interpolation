package com.example.pixelinterpolation.interpolator;

import com.example.pixelinterpolation.interpolator.strategy.ProgressiveInterpolationStrategy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProgressiveInterpolator extends AbstractInterpolator {

    private ProgressiveInterpolationStrategy strategy;
    private int bandWidth;

    public ProgressiveInterpolator(ImageView imageViewA, ImageView imageViewB, Image image1, Image image2, ProgressiveInterpolationStrategy strategy) {
        super(imageViewA, imageViewB, image1, image2);
        this.strategy = strategy;
        this.strategy.setDimensions(width, height);
    }

    @Override
    public void startInterpolation(Runnable onComplete) {

    }

    public void setBandWidth(int bandWidth) {
        this.bandWidth = bandWidth;
    }
}
