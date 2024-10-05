package com.example.pixelinterpolation.controller;

import com.example.pixelinterpolation.formatter.TextFieldFormatter;
import com.example.pixelinterpolation.interpolator.StandardInterpolator;
import com.example.pixelinterpolation.interpolator.InterpolationCallback;
import com.example.pixelinterpolation.interpolator.strategy.ProgressiveInterpolationStrategy;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class InterpolationController implements InterpolationCallback {

    @FXML
    public FlowPane flowPaneCenter;

    @FXML
    public RadioButton radioButtonStandard;
    @FXML
    public RadioButton radioButtonProgressive;

    @FXML
    public Label labelSteps;
    @FXML
    public Label labelDelay;

    @FXML
    public TextField textFieldSteps;
    @FXML
    public TextField textFieldDelay;

    @FXML
    public Label labelBand;
    @FXML
    public Label labelDirection;

    @FXML
    public ComboBox<Integer> comboBoxBand;
    @FXML
    public ComboBox<String> comboBoxDirection;

    @FXML
    public Button buttonStart;
    @FXML
    public Button buttonPause;
    @FXML
    public Button buttonReset;

    @FXML
    private GridPane gridPaneA;
    @FXML
    private GridPane gridPaneB;

    @FXML
    private ImageView imageViewA;
    @FXML
    private ImageView imageViewB;

    @FXML
    private StackPane stackPaneA1;
    @FXML
    private ImageView imageViewA1;
    @FXML
    private ImageView imageViewA2;
    @FXML
    private ImageView imageViewA3;
    @FXML
    private ImageView imageViewA4;
    @FXML
    private ImageView imageViewA5;
    @FXML
    private ImageView imageViewA6;
    @FXML
    private ImageView imageViewA7;
    @FXML
    private ImageView imageViewA8;

    @FXML
    private ImageView imageViewB1;
    @FXML
    private StackPane stackPaneB2;
    @FXML
    private ImageView imageViewB2;
    @FXML
    private ImageView imageViewB3;
    @FXML
    private ImageView imageViewB4;
    @FXML
    private ImageView imageViewB5;
    @FXML
    private ImageView imageViewB6;
    @FXML
    private ImageView imageViewB7;
    @FXML
    private ImageView imageViewB8;

    private StandardInterpolator interpolator;
//    private ProgressiveInterpolationStrategy progressiveInterpolator;

    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image image7;
    private Image image8;

    private Image selectedImageA;
    private Image selectedImageB;

    private StackPane selectedStackPaneA;
    private StackPane selectedStackPaneB;

    private static final int STEPS_MIN_VALUE = 1;
    private static final int STEPS_MAX_VALUE = 1000;
    private static final int STEPS_INITIAL_VALUE = 100;

    private static final int DELAY_MIN_VALUE = 0;
    private static final int DELAY_MAX_VALUE = 1000;
    private static final int DELAY_INITIAL_VALUE = 0;

    private static final int BAND_100 = 100;
    private static final int BAND_200 = 200;
    private static final int BAND_400 = 400;
    private static final int BAND_INITIAL_VALUE = BAND_200;

    private static final String DIRECTION_LEFT_TO_RIGHT = "Left to Right";
    private static final String DIRECTION_RIGHT_TO_LEFT = "Right to Left";
    private static final String DIRECTION_TOP_TO_BOTTOM = "Top to Bottom";
    private static final String DIRECTION_BOTTOM_TO_TOP = "Bottom to Top";

    private static final String DIRECTION_INITIAL_VALUE = DIRECTION_LEFT_TO_RIGHT;

    private static final String SELECTED_STYLE = "-fx-border-color: lightgreen; -fx-border-width: 5px;";
    private static final String DEFAULT_STYLE = "";

    private static final String IMAGE_PATH = "/com/example/pixelinterpolation/image/";

    @FXML
    public void initialize() {
        image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "01.png")));
        image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "02.png")));
        image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "14.png")));
        image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "13.png")));
        image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "11.png")));
        image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "07.png")));
        image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "06.png")));
        image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_PATH + "08.png")));

        imageViewA.setImage(image1);
        imageViewB.setImage(image2);

        imageViewA1.setImage(image1);
        imageViewA2.setImage(image2);
        imageViewA3.setImage(image3);
        imageViewA4.setImage(image4);
        imageViewA5.setImage(image5);
        imageViewA6.setImage(image6);
        imageViewA7.setImage(image7);
        imageViewA8.setImage(image8);

        imageViewB1.setImage(image1);
        imageViewB2.setImage(image2);
        imageViewB3.setImage(image3);
        imageViewB4.setImage(image4);
        imageViewB5.setImage(image5);
        imageViewB6.setImage(image6);
        imageViewB7.setImage(image7);
        imageViewB8.setImage(image8);

        interpolator = new StandardInterpolator(imageViewA, imageViewB, image1, image2);
        interpolator.setCallback(this);

        selectedStackPaneA = stackPaneA1;
        selectStackPane(selectedStackPaneA, true);
        selectedImageA = image1;

        selectedStackPaneB = stackPaneB2;
        selectStackPane(selectedStackPaneB, true);
        selectedImageB = image2;

        ToggleGroup group = new ToggleGroup();
        radioButtonStandard.setToggleGroup(group);
        radioButtonProgressive.setToggleGroup(group);
        radioButtonStandard.setSelected(true);

        TextFieldFormatter.setupTextFieldFormatter(textFieldSteps, STEPS_MIN_VALUE, STEPS_MAX_VALUE);
        TextFieldFormatter.setupTextFieldFormatter(textFieldDelay, DELAY_MIN_VALUE, DELAY_MAX_VALUE);

        textFieldSteps.setText(String.valueOf(STEPS_INITIAL_VALUE));
        textFieldDelay.setText(String.valueOf(DELAY_INITIAL_VALUE));

        comboBoxBand.getItems().addAll(
                BAND_100,
                BAND_200,
                BAND_400);
        comboBoxBand.setValue(BAND_INITIAL_VALUE);

        comboBoxDirection.getItems().addAll(
                DIRECTION_LEFT_TO_RIGHT,
                DIRECTION_RIGHT_TO_LEFT,
                DIRECTION_TOP_TO_BOTTOM,
                DIRECTION_BOTTOM_TO_TOP);
        comboBoxDirection.setValue(DIRECTION_INITIAL_VALUE);

        buttonPause.setDisable(true);
        buttonReset.setDisable(true);
        Platform.runLater(() -> buttonStart.requestFocus());
    }

    @FXML
    protected void onStartButtonClick(ActionEvent event) {
        interpolator.updateImages(selectedImageA, selectedImageB);

        if (textFieldSteps.getText().isEmpty()) {
            textFieldSteps.setText(String.valueOf(STEPS_INITIAL_VALUE));
        }
        if (textFieldDelay.getText().isEmpty()) {
            textFieldDelay.setText(String.valueOf(DELAY_INITIAL_VALUE));
        }

        interpolator.setSteps(textFieldSteps.getText().isEmpty() ? STEPS_INITIAL_VALUE : Integer.parseInt(textFieldSteps.getText()));
        interpolator.setDelay(textFieldDelay.getText().isEmpty() ? DELAY_INITIAL_VALUE : Integer.parseInt(textFieldDelay.getText()));

        interpolator.startInterpolation(() -> {
            interpolator.swapImages();
        });
    }

    @FXML
    protected void handleImageClickA(javafx.scene.input.MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        StackPane currentStackPane = (StackPane) clickedImageView.getParent();

        if (currentStackPane.equals(selectedStackPaneA)) {
            return;
        }

        if (clickedImageView.getImage().equals(imageViewB.getImage())) {
            return;
        }

        if (selectedStackPaneA != null) {
            selectStackPane(selectedStackPaneA, false);
        }

        selectedStackPaneA = currentStackPane;
        selectStackPane(selectedStackPaneA, true);

        selectedImageA = clickedImageView.getImage();
        imageViewA.setImage(selectedImageA);
    }

    @FXML
    protected void handleImageClickB(javafx.scene.input.MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        StackPane currentStackPane = (StackPane) clickedImageView.getParent();

        if (currentStackPane.equals(selectedStackPaneB)) {
            return;
        }

        if (clickedImageView.getImage().equals(imageViewA.getImage())) {
            return;
        }

        if (selectedStackPaneB != null) {
            selectStackPane(selectedStackPaneB, false);
        }

        selectedStackPaneB = currentStackPane;
        selectStackPane(selectedStackPaneB, true);

        selectedImageB = clickedImageView.getImage();
        imageViewB.setImage(selectedImageB);
    }

    private void setMiniaturesEnabled(boolean enabled) {
        updateGridPaneMiniatures(gridPaneA, enabled);
        updateGridPaneMiniatures(gridPaneB, enabled);
    }

    private void updateGridPaneMiniatures(GridPane gridPane, boolean enabled) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                for (Node stackChild : stackPane.getChildren()) {
                    if (stackChild instanceof ImageView imageView) {
                        imageView.setDisable(!enabled);
                        imageView.setOpacity(enabled ? 1.0 : 0.7);
                    }
                }
            }
        }
    }

    private void swapSelection() {
        Image tempImageA = selectedImageA;
        Image tempImageB = selectedImageB;

        for (Node node : gridPaneA.getChildren()) {
            if (node instanceof StackPane stackPane) {
                for (Node stackChild : stackPane.getChildren()) {
                    if (stackChild instanceof ImageView imageView) {
                        Image image = imageView.getImage();
                        if (image == tempImageB) {
                            selectStackPane(stackPane, true);
                            selectStackPane(selectedStackPaneA, false);
                            selectedStackPaneA = stackPane;
                            selectedImageA = image;
                        }
                    }
                }
            }
        }

        for (Node node : gridPaneB.getChildren()) {
            if (node instanceof StackPane stackPane) {
                for (Node stackChild : stackPane.getChildren()) {
                    if (stackChild instanceof ImageView imageView) {
                        Image image = imageView.getImage();
                        if (image == tempImageA) {
                            selectStackPane(stackPane, true);
                            selectStackPane(selectedStackPaneB, false);
                            selectedStackPaneB = stackPane;
                            selectedImageB = image;
                        }
                    }
                }
            }
        }
    }

    private void selectStackPane(StackPane stackPane, boolean selected) {
        stackPane.setStyle(selected ? SELECTED_STYLE : DEFAULT_STYLE);
    }

    @Override
    public void onInterpolationStarted() {
        radioButtonStandard.setDisable(true);
        radioButtonProgressive.setDisable(true);

        textFieldSteps.setDisable(true);
        textFieldDelay.setDisable(true);

        comboBoxBand.setDisable(true);
        comboBoxDirection.setDisable(true);

        buttonStart.setDisable(true);
        buttonPause.setDisable(false);
        buttonReset.setDisable(false);

        setMiniaturesEnabled(false);
    }

    @Override
    public void onInterpolationFinished() {
        radioButtonStandard.setDisable(false);
        radioButtonProgressive.setDisable(false);

        textFieldSteps.setDisable(false);
        textFieldDelay.setDisable(false);

        comboBoxBand.setDisable(false);
        comboBoxDirection.setDisable(false);

        buttonStart.setDisable(false);
        buttonPause.setDisable(true);
        buttonReset.setDisable(true);
        buttonStart.requestFocus();

        setMiniaturesEnabled(true);

        swapSelection();
    }

    @Override
    public void onInterpolationStopped() {
        radioButtonStandard.setDisable(false);
        radioButtonProgressive.setDisable(false);

        textFieldSteps.setDisable(false);
        textFieldDelay.setDisable(false);

        comboBoxBand.setDisable(false);
        comboBoxDirection.setDisable(false);

        buttonStart.setDisable(false);
        buttonPause.setDisable(true);
        buttonReset.setDisable(true);
        buttonStart.requestFocus();

        setMiniaturesEnabled(true);
    }

    @FXML
    public void handleRadioButtonStandardAction(ActionEvent actionEvent) {
        flowPaneCenter.getChildren().clear();
        flowPaneCenter.getChildren().addAll(labelSteps, textFieldSteps, labelDelay, textFieldDelay);
    }

    @FXML
    public void handleRadioButtonProgressiveAction(ActionEvent actionEvent) {
        flowPaneCenter.getChildren().clear();
        flowPaneCenter.getChildren().addAll(labelBand, comboBoxBand, labelDirection, comboBoxDirection);

    }

    @FXML
    public void onResetButtonClick(ActionEvent actionEvent) {
        interpolator.stopInterpolation();
    }

    @FXML
    public void onPauseButtonClick(ActionEvent actionEvent) {
    }
}