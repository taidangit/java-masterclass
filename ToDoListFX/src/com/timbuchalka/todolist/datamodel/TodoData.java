package com.timbuchalka.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TodoData {
    private static String fileName = "TodoListItems.txt";

    private static ObservableList<TodoItem> todoItems =  FXCollections.observableArrayList();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public static void addTodoItem(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    public void setTodoItems(ObservableList<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public static void loadTodoItem() throws IOException {

        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static void storeTodoItems() throws IOException {
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            Iterator<TodoItem> itemIterator = todoItems.iterator();
            while (itemIterator.hasNext()) {
                TodoItem item = itemIterator.next();
                bw.write(String.format("%s\t%S\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public static void deleteTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

}
