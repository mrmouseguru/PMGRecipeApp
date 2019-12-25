package guru.pmouse.recipe.services;

import guru.pmouse.recipe.domain.Recipe;
import guru.pmouse.recipe.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by PMouse Guru  on 12/22/2019
 */
class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
        Recipe returnRecipe = Recipe.builder().id(1L).build();
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));

        //then
        Recipe recipe = recipeService.findById(1L);

        assertNotNull(recipe,"Not null returned");
        assertNotNull(recipe.getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();


    }
}