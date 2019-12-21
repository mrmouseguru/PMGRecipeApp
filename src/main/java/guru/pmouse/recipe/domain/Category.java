package guru.pmouse.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by PMouse Guru  on 12/19/2019
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")

    private Set<Recipe> recipes;

}
