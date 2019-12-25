package com.timbuchalka;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testsqlite.db";
    public static final String CONNECTION_STRING =
            "jdbc:sqlite:D:\\HOC TAP\\HOC LAP TRINH\\LapTrinhJavaCoBan\\SourceCodeJava11MasterClass\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text" + ")");

            insertContact(statement, "An", 987654, "an@gmail.com");
            insertContact(statement, "Binh", 43554321, "binh@gmail.com");
            insertContact(statement, "Hanh", 96544321, "hanh@gmail.com");
            insertContact(statement, "Phuc", 964321, "phuc@gmail.com");
            insertContact(statement, "Giai", 43645321, "giai@gmail.com");
            insertContact(statement, "Thoat", 9465321, "thoat@gmail.com");

            statement.execute("UPDATE " + TABLE_CONTACTS +
                    " SET " + COLUMN_PHONE + "=123456789" +
                    " WHERE " + COLUMN_NAME + "='Hanh'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Thoat'");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(COLUMN_NAME) + "-"
                        + resultSet.getInt(COLUMN_PHONE) + "-" +
                        resultSet.getString(COLUMN_EMAIL));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS + " VALUES ('" + name + "'," + phone + ",'" + email + "')");
    }
}
