package com.timbuchalka;

import com.timbuchalka.model.Artist;
import com.timbuchalka.model.Datasource;
import com.timbuchalka.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can not open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_NONE);
        if (artists == null) {
            System.out.println("No artists");
            return;
        }

        for (Artist artist : artists) {
            System.out.println(artist);
        }

        List<String> albumsForArtist =
                datasource.queryAlbumsForArtist("Pink Floyd", Datasource.ORDER_BY_DESC);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_DESC);

        if (songArtists == null) {
            System.out.println("Could not find the artist for the song");
            return;
        }

        for (SongArtist songArtist : songArtists) {
            System.out.println(songArtist);
        }

        //get column name
        datasource.querySongsMetadata();

        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is:" + count);

        datasource.createViewForSongArtists();

        System.out.println("Enter a song title:");
        String title = new Scanner(System.in).nextLine();


        songArtists = datasource.querySongInfoView(title);
        if (songArtists == null) {
            System.out.println("Could not find the artist for the song");
            return;
        }

        for (SongArtist songArtist : songArtists) {
            System.out.println(songArtist);
        }

        datasource.insertSong("Like A Rolling Stone", "Bod Dylan", "Bod Dylan's Greatest Hits", 5);

        datasource.close();
    }
}
