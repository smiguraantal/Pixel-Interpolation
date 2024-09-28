package com.example.pixelinterpolation.interpolator;

public interface InterpolationCallback {

    void onInterpolationFinished();
    void onInterpolationStarted();
    void onInterpolationStopped();
}