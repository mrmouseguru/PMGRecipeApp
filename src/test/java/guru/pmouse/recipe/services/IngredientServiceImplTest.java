package guru.pmouse.recipe.services;

import guru.pmouse.recipe.commands.IngredientCommand;
import guru.pmouse.recipe.converters.IngredientToIngredientCommand;
import guru.pmouse.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.pmouse.recipe.domain.Ingredient;
import guru.pmouse.recipe.domain.Recipe;
import guru.pmouse.recipe.repositories.RecipeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by PMouse Guru  on 01/01/2020
 */
@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    //@InjectMocks
    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {

        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);

    }

    @Test
    void findByRecipeIdAndIngredientIdReal() {
        //given
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when

        assertNotNull(ingredientCommand);

        verify(recipeRepository, times(1)).findById(anyLong());

        assertEquals(3L, ingredientCommand.getId());

        assertEquals(1L, ingredientCommand.getRecipeId());

    }

    @Test
    void findByRecipeIdAndIngredientIdFullMock() {
        /*//given
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);


        IngredientCommand ingredientCommandReturn = new IngredientCommand();
        ingredientCommandReturn.setId(2L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);


        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommandReturn);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when

        assertNotNull(ingredientCommand);

        verify(ingredientToIngredientCommand).convert(any());

        verify(recipeRepository, times(1)).findById(anyLong());

        assertEquals(2L, ingredientCommand.getId());

        // assertEquals(1L, ingredientCommand.getRecipeId());*/

    }
}