package com.timbuchalka.todolist;

import com.timbuchalka.todolist.datamodel.TodoData;
import com.timbuchalka.todolist.datamodel.TodoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private TextArea itemDetails;

    @FXML
    private ListView todoListView;

    @FXML
    private Label deadlineLabel;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;

    private SortedList<TodoItem> sortedList;

    private Predicate<TodoItem> getAllItems;
    private Predicate<TodoItem> getTodaysItems;

    public void initialize() {
       /* TodoItem item1 = new TodoItem("Mail birth card", "Buy a 30th birth card for John",
                LocalDate.of(2019, Month.MARCH, 28));
        TodoItem item2 = new TodoItem("Doctor's Appointment", "See Doctor Smith 123 main street.Bring paper work.",
                LocalDate.of(2019, Month.APRIL, 27));
        TodoItem item3 = new TodoItem("Finish design proposal for client", "I promised Mike I've email website",
                LocalDate.of(2019, Month.DECEMBER, 26));
        TodoItem item4 = new TodoItem("Pick up Dog at the train station", "Dog arriving on March 28 on the 5:00 train",
                LocalDate.of(2019, Month.AUGUST, 1));
        TodoItem item5 = new TodoItem("Pick up dry cleaning", "The clothes should be by ready by Wednesday",
                LocalDate.of(2019, Month.JULY, 24));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        TodoData.setTodoItems(todoItems);*/

        getAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        getTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return item.getDeadline().equals(LocalDate.now());
            }
        };

        filteredList = new FilteredList<TodoItem>(TodoData.getTodoItems(), getAllItems);

        sortedList = new SortedList<TodoItem>(filteredList,
                new Comparator<TodoItem>() {
                    @Override
                    public int compare(TodoItem item1, TodoItem item2) {
                        return item1.getDeadline().compareTo(item2.getDeadline());
                    }
                });

        //todoListView.setItems(TodoData.getTodoItems());

        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                TodoItem itemSelected = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
                if (itemSelected != null) {
                    itemDetails.setText(itemSelected.getDetails());

                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(itemSelected.getDeadline()));
                }

            }
        });

        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        listContextMenu.getItems().addAll(deleteMenuItem);
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TodoItem itemSelected = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
                deleteItem(itemSelected);

            }
        });


        todoListView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                ListCell<TodoItem> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(TodoItem todoItem, boolean b) {
                        super.updateItem(todoItem, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(todoItem.getShortDescription());
                            if (todoItem.getDeadline().isBefore(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.RED);
                            } else if (todoItem.getDeadline().equals(LocalDate.now())) {
                                setTextFill(Color.BROWN);

                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }

                        });


                return cell;
            }
        });


    }

    @FXML
    public void showViewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            DialogController controller = loader.getController();
            TodoItem newItem = controller.processResults();
            todoListView.getSelectionModel().select(newItem);
        }
    }


    public void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or Cancel to back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            TodoData.deleteTodoItem(item);
            //todoListView.setItems(sortedList);
        }
    }

    @FXML
    public void handleFilterButton() {
        TodoItem selectedItem = (TodoItem) todoListView.getSelectionModel().getSelectedItem();

        if (filterToggleButton.isSelected()) {
            filteredList.setPredicate(getTodaysItems);
            if (filteredList.isEmpty()) {
                itemDetails.clear();
                deadlineLabel.setText("");
            }

        } else {
            filteredList.setPredicate(getAllItems);

        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
/*
    @FXML
    public void handelClickListView() {
        TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
        itemDetails.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());

    }*/
}
