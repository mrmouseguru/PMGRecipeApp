package guru.pmouse.recipe.services;

import guru.pmouse.recipe.domain.Recipe;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

}
