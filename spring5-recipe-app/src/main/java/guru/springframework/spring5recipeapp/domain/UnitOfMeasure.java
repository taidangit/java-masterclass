package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    /*@OneToOne(mappedBy ="uom")
    private Ingredient ingredient;*/


}
