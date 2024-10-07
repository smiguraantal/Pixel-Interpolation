package com.example.pixelinterpolation.interpolator;

public interface InterpolationCallback {

    void onInterpolationStarted();
    void onInterpolationFinished();
    void onInterpolationStopped();
}