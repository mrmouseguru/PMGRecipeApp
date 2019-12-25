package guru.pmouse.recipe.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by PMouse Guru  on 12/12/2019
 */
@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
