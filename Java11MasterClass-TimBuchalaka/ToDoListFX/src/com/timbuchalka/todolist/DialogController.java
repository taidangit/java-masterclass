package com.timbuchalka.todolist;

import com.timbuchalka.todolist.datamodel.TodoData;
import com.timbuchalka.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResults() {
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineVaue = deadlinePicker.getValue();

        TodoItem newItem=new TodoItem(shortDescription, details, deadlineVaue);
        TodoData.addTodoItem(newItem);
        return newItem;
    }
}
