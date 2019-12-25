package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {

    @FXML
    private ListView listView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    // private Task<ObservableList<String>> task;

    private Service<ObservableList<String>> service;

    public void initialize() {
       /* task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                String[] names = {
                        "Tim Buchalka",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald"};

                ObservableList<String> employess = FXCollections.observableArrayList();

                for (int i = 0; i < names.length; i++) {
                    employess.add(names[i]);
                    updateMessage("Added "+names[i]+" to the list");

                    updateProgress(i + 1, 6);
                    Thread.sleep(1000);
                }
                return employess;
            }
        };*/

        service = new EmployeeService();

        /*service.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressBar.setVisible(true);
                progressLabel.setVisible(true);
            }
        });

        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressBar.setVisible(false);
                progressLabel.setVisible(false);
            }
        });*/

       /* service.setOnRunning(e -> {
            progressBar.setVisible(true);
            progressLabel.setVisible(true);
        });

        service.setOnSucceeded(e -> {
            progressBar.setVisible(false);
            progressLabel.setVisible(false);
        });*/

        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());

        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
    }

    @FXML
    public void buttonPressed() {
        if (service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        } else if (service.getState() == Service.State.READY) {
            service.start();
        }
    }
}
