package guru.springframework.spring5reactivemongorecipeapp.command;

import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryCommand {

    private String id;
    private String description;
    private List<Recipe> recipes=new ArrayList<>();

}
