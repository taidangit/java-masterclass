package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Datasource;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView artistTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ContextMenu contextMenu;


    public void initialize() {

        contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");

        contextMenu.getItems().addAll(deleteMenuItem);

        artistTable.setContextMenu(contextMenu);

        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteArtist();
            }
        });

    }

    @FXML
    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtistId() {
        Artist artistSelected = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if (artistSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No artist selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the artist.");
            alert.showAndWait();
            return;
        }

        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(artistSelected.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateArtist() {
        Artist artistSelected = (Artist) artistTable.getSelectionModel().getSelectedItem();

        if (artistSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No artist selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the artist you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Artist");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("artist-dialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Could not dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ArtistController artistController = loader.getController();
        artistController.editArtist(artistSelected);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    String artistUpdate = artistController.updateArtist(artistSelected);
                    return Datasource.getInstance().updateArtistName(artistSelected.getId(), artistUpdate);
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }

    }

    @FXML
    public void addArtist() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Artist");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("artist-dialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog.");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    ArtistController artistController = loader.getController();
                    String newArtistName = artistController.getNewArtist();
                    return Datasource.getInstance().insertArtist(newArtistName);
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }
    }

    public void deleteArtist() {
        Artist artistSelected = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if (artistSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Artist Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the artist you want to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Artist");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected artist: " +
                artistSelected.getName()+"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().deleteArtist(artistSelected.getId());
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }
    }

}

class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artist> call() throws Exception {
        return FXCollections.observableArrayList(Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}

