package com.example.pixelinterpolation.interpolator;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractInterpolator {

    protected InterpolationCallback callback;

    protected ImageView imageViewA;
    protected ImageView imageViewB;

    protected Image image1;
    protected Image image2;

    protected int width;
    protected int height;

    protected boolean isStopped;
    protected boolean isPaused;

    protected AnimationTimer animationTimer;

    public AbstractInterpolator(ImageView imageViewA, ImageView imageViewB, Image image1, Image image2) {
        this.imageViewA = imageViewA;
        this.imageViewB = imageViewB;
        this.image1 = image1;
        this.image2 = image2;
        this.width = (int) image1.getWidth();
        this.height = (int) image1.getHeight();
    }

    public abstract void startInterpolation(Runnable onComplete);

    public void updateImages(Image newImageA, Image newImageB) {
        this.image1 = newImageA;
        this.image2 = newImageB;
        imageViewA.setImage(image1);
        imageViewB.setImage(image2);
    }

    public void swapImages() {
        Image temp = image1;
        image1 = image2;
        image2 = temp;

        imageViewA.setImage(image1);
        imageViewB.setImage(image2);
    }

    public void setCallback(InterpolationCallback callback) {
        this.callback = callback;
    }

    public void pauseInterpolation() {
        isPaused = true;
        animationTimer.stop();
    }

    public void resumeInterpolation() {
        isPaused = false;
        animationTimer.start();
    }

    public void stopInterpolation() {
        isStopped = true;
        if (animationTimer != null) {
            animationTimer.stop();
        }
        updateImages(image1, image2);
        callback.onInterpolationStopped();
    }

    public boolean isPaused() {
        return isPaused;
    }
}