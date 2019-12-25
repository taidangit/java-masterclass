package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameFiled;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;

    @FXML
    private Label ourLabel;

    @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    private CheckBox ourCheckBox;

    @FXML
    public void onButtonClicked(ActionEvent event) {
        //System.out.println(event.getSource());
        if (event.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameFiled.getText());
        } else {
            System.out.println("Byte, " + nameFiled.getText());
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I am going to sleep on the: " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I am updating the label on the: " + s);
                            ourLabel.setText("We did something");
                        }
                    });

                } catch (InterruptedException event) {

                }

            }
        };

        new Thread(task).start();

        if (ourCheckBox.isSelected()) {
            nameFiled.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }


    @FXML
    public void handleKeyReleased() {
        String text = nameFiled.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    @FXML
    public void handleChange() {
        System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));

    }
}
