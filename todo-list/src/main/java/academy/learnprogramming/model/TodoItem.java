package academy.learnprogramming.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
public class TodoItem {
    private int id;
    private String title;
    private String detail;
    private LocalDate deadline;

    public TodoItem(String title, String detail, LocalDate deadline) {
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
    }


}
