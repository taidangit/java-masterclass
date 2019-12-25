package academy.learnprogramming.common;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Album {
    private SimpleIntegerProperty id=new SimpleIntegerProperty();
    private SimpleStringProperty name=new SimpleStringProperty();
    private SimpleIntegerProperty artistId=new SimpleIntegerProperty();

    public Album(int id, String name, int artistId) {
        this.id.set(id);
        this.name.set(name);
        this.artistId.set(artistId);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getArtistId() {
        return artistId.get();
    }

    public SimpleIntegerProperty artistIdProperty() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId.set(artistId);
    }
}
