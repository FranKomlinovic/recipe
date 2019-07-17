package hr.brocom.recept.service;

import hr.brocom.recept.domain.jpa.RecipeJpaImpl;
import hr.brocom.recept.model.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeJpaImpl recipeJpa;

    public List<RecipeDto> getAllRecipes() {
        return recipeJpa.getAllRecipes();
    }

    public void addRecipe(RecipeDto recipeDto) {
        recipeJpa.addRecipe(recipeDto);
    }

    public void updateRecipe(RecipeDto recipeDto) {
        recipeJpa.updateRecipe(recipeDto);
    }

    public void deactivateRecipe(String code) {
        recipeJpa.deactivateRecipe(code);
    }
}
