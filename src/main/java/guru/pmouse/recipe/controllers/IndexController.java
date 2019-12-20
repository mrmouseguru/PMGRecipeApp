package guru.pmouse.recipe.controllers;

import guru.pmouse.recipe.domain.Category;
import guru.pmouse.recipe.domain.UnitOfMeasure;
import guru.pmouse.recipe.repositories.CategoryRepository;
import guru.pmouse.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Guru Mouse on 12/07/2019
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(){

        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id: " + category.get().getId());
        System.out.println("UOM Id: " + unitOfMeasure.get().getId());

        return "index";
    }
}
