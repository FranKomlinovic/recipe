package hr.brocom.recept.controller;

import hr.brocom.recept.model.RecipeDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    RecipeService recipeService;

    @GetMapping("/list")
    public RestDto<List<RecipeDto>> getAllActiveRecipes() {
        log.trace("Get all active recipes");
        long time = System.currentTimeMillis();
        List<RecipeDto> recipes = recipeService.getAllRecipes();
        log.trace("getAllActiveRecipes finished in {} ms", System.currentTimeMillis() - time);
        log.trace("getAllActiveRecipes returned {} results", recipes.size());
        return RestDto.success(recipes);
    }
}