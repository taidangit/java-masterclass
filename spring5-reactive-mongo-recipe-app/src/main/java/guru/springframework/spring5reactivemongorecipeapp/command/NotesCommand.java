package guru.springframework.spring5reactivemongorecipeapp.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesCommand {

    private String id;
    private String recipeNotes;

}