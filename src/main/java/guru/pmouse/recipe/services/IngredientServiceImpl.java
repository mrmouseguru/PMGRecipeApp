package guru.pmouse.recipe.services;

import guru.pmouse.recipe.commands.IngredientCommand;
import guru.pmouse.recipe.converters.IngredientToIngredientCommand;
import guru.pmouse.recipe.domain.Recipe;
import guru.pmouse.recipe.repositories.RecipeRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by PMouse Guru  on 12/31/2019
 */
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional
                = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Ingredient id not found. Id: " + ingredientId);
        }
        return ingredientCommandOptional.get();
    }
}
