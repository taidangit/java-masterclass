package guru.springframework.spring5recipeapp.command;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

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
    /* private MultipartFile image;*/

    private Set<IngredientCommand> ingredients = new HashSet<>();

    private Difficulty difficulty;

    private NotesCommand notes;

    private Set<CategoryCommand> categories = new HashSet<>();
}
