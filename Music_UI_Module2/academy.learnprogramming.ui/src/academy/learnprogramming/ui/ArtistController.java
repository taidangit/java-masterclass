package academy.learnprogramming.ui;

import academy.learnprogramming.common.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ArtistController {
    @FXML
    private TextField artistField;

    public String getNewArtist() {
        String artistName = artistField.getText().trim();

        return artistName;
    }

    public void editArtist(Artist artist) {
        artistField.setText(artist.getName());
    }

    public String updateArtist(Artist artist) {
        artist.setName(artistField.getText());
        return artist.getName();
    }
}
