package guru.springframework.spring5reactivemongorecipeapp.command;

import guru.springframework.spring5reactivemongorecipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeCommand {

    private String id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(value=1)
    @Max(value=999)
    private Integer prepTime;

    @NotNull
    @Min(value=1)
    @Max(value=999)
    private Integer cookTime;

    @NotNull
    @Min(value=1)
    @Max(value=100)
    private Integer servings;

    @NotBlank
    @Size(min = 3, max = 255)
    private String source;

    @NotBlank
    @URL
    private String url;

    private String directions;

    private Byte[] image;

    private List<IngredientCommand> ingredients=new ArrayList<>();

    private Difficulty difficulty;

    private NotesCommand notes;

    private List<CategoryCommand> categories=new ArrayList<>();

}
