# Pixel Interpolation

## Overwiev

Pixel interpolation is the process of smoothly transitioning the colors of corresponding pixels between two images over a set number of steps, creating a gradual visual transformation from one image to another.

## Requirements and Setup

The program is built using the JavaFX framework, which requires proper configuration of the JavaFX libraries. The following dependencies are necessary to run the project:

- `javafx-controls`

- `javafx-fxml`

To start the application, run the `PixelInterpolation.java` file located in the `com.example.pixelinterpolation` package.

## How It Works

The pixel interpolation process occurs in the following steps:

__Loading Image Data:__ The application loads the first and second images and retrieves their pixel data. Each pixel's color is stored in RGB (red, green, blue) format.

__Accessing Pixels:__ The program iterates through the pixels of the images. For each pixel, it associates the RGB values of the corresponding pixels from both images.

__Determining the Number of Interpolation Steps:__ The user can specify the desired number of steps, which determines how many gradual transitions they want to see between the images. This number affects the smoothness and continuity of the transition.

__Calculating Intermediate Color Values:__ For each pixel, the program calculates the intermediate RGB values using the following formula:

`Interpolated color = Starting color + (Target color − Starting color) / Number of steps x Current step`

Using this formula, each pixel's color gradually approaches the color of the corresponding pixel in the second image.

__Delay:__ The program applies a delay between each step, allowing the user to observe the transitions between images for a longer period. The amount of delay can influence the speed and experience of the animation.

__Updating the Image:__ After the program calculates the new RGB values, it updates the image to display the current interpolated state.

__Final Image Display:__ At the end of the interpolation, the pixel colors reach those of the second image, allowing the user to see a smooth and gradual transformation from the first image to the second.

## Screenshots

<img src=docs/image01.png alt="">
<br>

| ![Kép A0](docs/savedImageA0.png)  | ![Kép A25](docs/savedImageA25.png) | ![Kép A50](docs/savedImageA50.png) | ![Kép A75](docs/savedImageA75.png) | ![Kép A100](docs/savedImageA100.png) |
|-----------------------------------|------------------------------------|------------------------------------|------------------------------------|--------------------------------------|
| ![Kép B0](docs/savedImageB0.png)  | ![Kép B25](docs/savedImageB25.png) | ![Kép B50](docs/savedImageB50.png) | ![Kép B75](docs/savedImageB75.png) | ![Kép B100](docs/savedImageB100.png) |
| ![Kép C0](docs/savedImageC0.png)  | ![Kép C25](docs/savedImageC25.png) | ![Kép C50](docs/savedImageC50.png) | ![Kép C75](docs/savedImageC75.png) | ![Kép C100](docs/savedImageC100.png) |
| ![Kép D0](docs/savedImageD0.png)  | ![Kép D25](docs/savedImageD25.png) | ![Kép D50](docs/savedImageD50.png) | ![Kép D75](docs/savedImageD75.png) | ![Kép D100](docs/savedImageD100.png) |