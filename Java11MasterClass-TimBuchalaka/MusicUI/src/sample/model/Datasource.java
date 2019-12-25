package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";

    public static final String CONNECTION_STRING =
            "jdbc:sqlite:D:\\HOC TAP\\HOC LAP TRINH\\LapTrinhJavaCoBan\\SourceCodeJava11MasterClass\\MusicUI\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


    public static final String QUERY_ALBUMS_BY_ARTIST_ID =
            "SELECT * FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_ARTIST +
                    " = ? ORDER BY " + COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    public static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_ARTISTS + " SET " +
            COLUMN_ARTIST_NAME + " = ? WHERE " + COLUMN_ARTIST_ID + " = ?";

    public static final String INSERT_ARTISTS = "INSERT INTO " + TABLE_ARTISTS +
            "(" + COLUMN_ARTIST_NAME + ")" + " VALUES(?)";

    public static final String DELETE_ARTISTS = "DELETE FROM " + TABLE_ARTISTS +
            " WHERE " + COLUMN_ARTIST_ID + " = ?";

    private Connection conn;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement queryAlbumsByArtistId;
    private PreparedStatement updateArtistName;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement deleteArtist;

    private static Datasource instance = new Datasource();

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);

            updateArtistName = conn.prepareStatement(UPDATE_ARTIST_NAME);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTISTS);

            deleteArtist = conn.prepareStatement(DELETE_ARTISTS);

            return true;

        } catch (SQLException e) {
            System.out.println("Could not connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if (queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }

            if (updateArtistName != null) {
                updateArtistName.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if(deleteArtist != null) {
                deleteArtist.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();

            while (resultSet.next()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                }

                Artist artist = new Artist(
                        resultSet.getInt(INDEX_ARTIST_ID),
                        resultSet.getString(INDEX_ARTIST_NAME));

                artists.add(artist);
            }
            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Album> queryAlbumsForArtistId(int artistId) {
        try {
            queryAlbumsByArtistId.setInt(1, artistId);
            ResultSet resultSet = queryAlbumsByArtistId.executeQuery();

            List<Album> albums = new ArrayList<>();
            while (resultSet.next()) {

                Album album = new Album(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                albums.add(album);
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public boolean updateArtistName(int artistId, String artistName) {
        try {
            updateArtistName.setString(1, artistName);
            updateArtistName.setInt(2, artistId);

            int affectRecord = updateArtistName.executeUpdate();

            return affectRecord == 1;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }

    public boolean insertArtist(String artistName) {
        try {
            insertIntoArtists.setString(1, artistName);

            int affectRecord = insertIntoArtists.executeUpdate();

            return affectRecord == 1;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteArtist(int artistId) {
        try {
            deleteArtist.setInt(1, artistId);

            int affectRecord = deleteArtist.executeUpdate();

            return affectRecord == 1;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }
}
