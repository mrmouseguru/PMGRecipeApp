package guru.pmouse.recipe.services;

import guru.pmouse.recipe.commands.RecipeCommand;
import guru.pmouse.recipe.converters.RecipeCommandToRecipe;
import guru.pmouse.recipe.converters.RecipeToRecipeCommand;
import guru.pmouse.recipe.domain.Recipe;
import guru.pmouse.recipe.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by PMouse Guru  on 12/22/2019
 */
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    public static final long ID = 1L;
    public static final long RECIPE_ID = 1L;
    public static final long RECIPE_COMMAND_ID = RECIPE_ID;
    public static final long SAVED_RECIPE_ID = 2L;
    @InjectMocks
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.initMocks(this);
        //recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    void findById() {
        //given
        Recipe returnRecipe = Recipe.builder().id(1L).build();
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));
        Recipe recipe = recipeService.findById(1L);
        //then
        assertNotNull(recipe, "Not null returned");
        assertNotNull(recipe.getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void saveRecipeCommand() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);

        Recipe detachedRecipe = new Recipe();
        detachedRecipe.setId(RECIPE_ID);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(RECIPE_ID);

        RecipeCommand returnRecipeCommand = new RecipeCommand();
        returnRecipeCommand.setId(RECIPE_ID);
        //when
        when(recipeCommandToRecipe.convert(any())).thenReturn(detachedRecipe);

        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        when(recipeToRecipeCommand.convert(any())).thenReturn(returnRecipeCommand);

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        //then
        assertNotNull(savedRecipeCommand);
        assertEquals(savedRecipeCommand.getId(), recipeCommand.getId());
        verify(recipeCommandToRecipe, times(1)).convert(any());
        verify(recipeRepository, times(1)).save(any());
        verify(recipeToRecipeCommand, times(1)).convert(any());
    }

    @Test
    void findRecipeCommandById() {
        //given
        RecipeCommand returnRecipeCommand = new RecipeCommand();
        returnRecipeCommand.setId(RECIPE_COMMAND_ID);
        //when
        when(recipeToRecipeCommand.convert(any())).thenReturn(returnRecipeCommand);
        RecipeCommand foundRecipeCommand = recipeService.findCommandById(RECIPE_COMMAND_ID);
        //then
        verify(recipeToRecipeCommand).convert(any());
        assertNotNull(foundRecipeCommand);
        assertEquals(RECIPE_COMMAND_ID, foundRecipeCommand.getId());
    }

    @Test
    void testDeleteRecipeById() {
        //given
        Long idToDelete = 1L;
        //then
        recipeService.deleteById(idToDelete);
        //when
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}