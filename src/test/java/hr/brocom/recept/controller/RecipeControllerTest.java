package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.ProductJpaImpl;
import hr.brocom.recept.domain.jpa.entity.RecipeEntity;
import hr.brocom.recept.domain.jpa.repository.RecipeRepository;
import hr.brocom.recept.model.ProductRecipeDto;
import hr.brocom.recept.model.RecipeDto;
import hr.brocom.recept.model.RestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeControllerTest {

    private static final String CODE = "REC1";
    private static final String NAME = "Recept1";
    private static final String TEXT = "Stavi recept negdje";
    private static final LocalDateTime CREATED_DATE_TIME = LocalDateTime.now();

    private static final String INIT_CODE = "REC0";
    private static final String INIT_NAME = "Recept";
    private static final String INIT_TEXT = "Napravi bilo sta";
    private static final LocalDateTime INIT_CREATED_DATE_TIME = LocalDateTime.of(2019, 7, 10, 13, 50, 45);
    private static final String INT_PRODUCT_CODE = "PRO0";

    @Autowired
    RecipeController recipeController;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ProductJpaImpl productJpa;

    @Test
    @Transactional
    public void getAllActiveRecipes() {
        RestDto<List<RecipeDto>> response = recipeController.getAllActiveRecipes();

        assertNotNull(response.getData());
        assertTrue(response.isSuccess());
        Optional<RecipeDto> recipeOptional = response.getData()
                .stream()
                .filter(recipeDto -> INIT_CODE.equals(recipeDto.getCode()))
                .findFirst();

        assertTrue(recipeOptional.isPresent());
        RecipeDto recipeDto = recipeOptional.get();

        assertTrue(recipeDto.getRecommendedProducts().isEmpty());
        assertTrue(recipeDto.getProductRecipeList().isEmpty());
        assertEquals(INIT_CODE, recipeDto.getCode());
        assertEquals(INIT_NAME, recipeDto.getName());
        assertEquals(INIT_CREATED_DATE_TIME, recipeDto.getCreatedTime());
        assertEquals(INIT_TEXT, recipeDto.getText());
    }

    @Test
    @Transactional
    public void addRecipe() {
        recipeController.addRecipe(createInitRecipe());
        RecipeEntity recipeFromDatabase = findByCode(CODE);
        assertEquals(CODE, recipeFromDatabase.getCode());
        assertEquals(NAME, recipeFromDatabase.getName());
        assertEquals(TEXT, recipeFromDatabase.getText());
        assertEquals(CREATED_DATE_TIME, recipeFromDatabase.getCreatedTime());

    }

    @Test
    @Transactional
    public void updateRecipe() {
        // Init data
        RecipeDto initRecipe = createInitRecipe();

        // Check if not exists yet
        assertFalse(recipeRepository.findByCode(CODE)
                .isPresent());

        // Check if values from init are present
        Optional<RecipeEntity> entityFromInit = recipeRepository.findByCode(INIT_CODE);
        assertTrue(entityFromInit.isPresent());
        RecipeEntity recipeEntity = entityFromInit.get();

        // Assert if everything is as in import script
        assertEquals(INIT_TEXT, recipeEntity.getText());
        assertEquals(INIT_CREATED_DATE_TIME, recipeEntity.getCreatedTime());
        assertEquals(INIT_NAME, recipeEntity.getName());
        assertEquals(INIT_CODE, recipeEntity.getCode());
        assertNotNull(recipeEntity.getProductRecipeList());
        assertNotNull(recipeEntity.getRecommendedProducts());

        // Change information from import script to information from initRecipe method by changing code
        initRecipe.setCode(INIT_CODE);
        initRecipe.setProductRecipeList(createProductRecipeList());
        // Execute update
        recipeController.updateRecipe(initRecipe);

        RecipeEntity updatedRecipe = findByCode(INIT_CODE);

        assertEquals(TEXT, updatedRecipe.getText());
        assertEquals(CREATED_DATE_TIME, updatedRecipe.getCreatedTime());
        assertEquals(NAME, updatedRecipe.getName());
        assertEquals(INIT_CODE, updatedRecipe.getCode());

    }

    @Test
    @Transactional
    public void deactivateRecipe() {
        RecipeEntity recipeByCode = findByCode(INIT_CODE);

        assertTrue(recipeByCode.getActive());

        recipeController.deactivateRecipe(INIT_CODE);

        recipeByCode = findByCode(INIT_CODE);

        assertFalse(recipeByCode.getActive());
    }

    private List<ProductRecipeDto> createProductRecipeList() {
        List<ProductRecipeDto> list = new ArrayList<>();

        ProductRecipeDto productRecipeDto = new ProductRecipeDto();
        productRecipeDto.setProduct(productJpa.findProductByCode(INT_PRODUCT_CODE));
        productRecipeDto.setQuantity(3);

        list.add(productRecipeDto);

        return list;

    }

    private RecipeDto createInitRecipe() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setCode(CODE);
        recipeDto.setCreatedTime(CREATED_DATE_TIME);
        recipeDto.setName(NAME);
        recipeDto.setText(TEXT);
        recipeDto.setProductRecipeList(createProductRecipeList());
        return recipeDto;
    }

    private RecipeEntity findByCode(String code) {
        return recipeRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }
}