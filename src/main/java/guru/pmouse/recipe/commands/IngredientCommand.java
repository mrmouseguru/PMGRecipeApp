package guru.pmouse.recipe.commands;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by PMouse Guru  on 12/17/2019
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
