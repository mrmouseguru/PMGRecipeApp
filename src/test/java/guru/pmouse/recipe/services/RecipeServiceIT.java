package guru.pmouse.recipe.services;

import guru.pmouse.recipe.commands.RecipeCommand;
import guru.pmouse.recipe.converters.RecipeToRecipeCommand;
import guru.pmouse.recipe.domain.Recipe;
import guru.pmouse.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by PMouse Guru  on 12/26/2019
 */
@SpringBootTest
class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;

    public static final String NEW_DESCRIPTION = "description";
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void saveRecipeCommand() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();//get first
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);
        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipeCommand.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipeCommand.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipeCommand.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}