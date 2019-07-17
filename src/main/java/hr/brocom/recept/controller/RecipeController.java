package hr.brocom.recept.controller;

import hr.brocom.recept.model.RecipeDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("")
    public RestDto<Void> addRecipe(@RequestBody @Valid RecipeDto recipeDto) {
        log.info("Adding recipe: {}...", recipeDto.getCode());
        log.trace("Recipe: {}", recipeDto);
        long time = System.currentTimeMillis();
        recipeService.addRecipe(recipeDto);
        log.trace("addRecipe finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Recipe uspješno dodan!");
    }

    @PutMapping("")
    public RestDto<Void> updateRecipe(@RequestBody RecipeDto recipeDto) {
        log.info("Update recipe: {}...", recipeDto.getCode());
        log.trace("Recipe: " + recipeDto);
        long time = System.currentTimeMillis();
        recipeService.updateRecipe(recipeDto);
        log.trace("updateRecipe finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Recipe uspješno ažuriran");
    }

    @DeleteMapping("/{code}")
    public RestDto<Void> deactivateRecipe(@PathVariable String code) {
        log.info("Deleting recipe: {}...", code);
        long time = System.currentTimeMillis();
        recipeService.deactivateRecipe(code);
        log.trace("deleteRecipe finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Recipe uspješno obrisan");
    }
}