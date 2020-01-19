package guru.pmouse.recipe.controllers;

import guru.pmouse.recipe.commands.RecipeCommand;
import guru.pmouse.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by PMouse Guru  on 12/23/2019
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findById((id)));

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    //@RequestMapping(name = "recipe", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show" ;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable Long id){
        log.debug("Preparing for delete Recipe ...");
        recipeService.deleteById(id);
        log.debug("Deleted Recipe");
        return "redirect:/";
    }

}
