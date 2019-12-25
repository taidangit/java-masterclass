package guru.springframework.spring5recipeapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   /* @OneToOne(mappedBy = "notes")
    private Recipe recipe;
*/
    @Lob
    @Column(name = "recipe_notes")
    private String recipeNotes;

}
