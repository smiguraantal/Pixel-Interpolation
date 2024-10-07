package com.example.pixelinterpolation.interpolator;

import com.example.pixelinterpolation.interpolator.strategy.BottomToTopInterpolationStrategy;
import com.example.pixelinterpolation.interpolator.strategy.DirectionalInterpolationStrategy;
import com.example.pixelinterpolation.interpolator.strategy.TopToBottomInterpolationStrategy;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class DirectionalInterpolator extends AbstractInterpolator implements Interpolator {

    private final DirectionalInterpolationStrategy strategy;
    private int bandWidth;

    public DirectionalInterpolator(ImageView imageViewA, ImageView imageViewB, Image image1, Image image2, DirectionalInterpolationStrategy strategy) {
        super(imageViewA, imageViewB, image1, image2);
        this.strategy = strategy;
        this.strategy.setDimensions(width, height);
    }

    @Override
    public void startInterpolation(Runnable onComplete) {
        callback.onInterpolationStarted();

        if (animationTimer != null) {
            animationTimer.stop();
        }

        animationTimer = new AnimationTimer() {
            int step = 1;

            final int maxSteps = strategy instanceof TopToBottomInterpolationStrategy || strategy instanceof BottomToTopInterpolationStrategy
                    ? (int) (height / (bandWidth / 100) + 100)
                    : (int) (width / (bandWidth / 100) + 100);

            @Override
            public void handle(long now) {
                if (isStopped) {
                    stop();
                    onComplete.run();
                    callback.onInterpolationFinished();
                    return;
                }

                if (isPaused) {
                    return;
                }

                WritableImage interpolatedImage = strategy.interpolate(image1, image2, step, bandWidth);
                imageViewA.setImage(interpolatedImage);

                interpolatedImage = strategy.interpolate(image2, image1, step, bandWidth);
                imageViewB.setImage(interpolatedImage);

                step++;

                if (step > maxSteps) {
                    this.stop();
                    if (onComplete != null) {
                        onComplete.run();
                    }
                    callback.onInterpolationFinished();
                }
            }
        };

        isStopped = false;
        isPaused = false;
        animationTimer.start();
    }

    public void setBandWidth(int bandWidth) {
        this.bandWidth = bandWidth;
    }
}