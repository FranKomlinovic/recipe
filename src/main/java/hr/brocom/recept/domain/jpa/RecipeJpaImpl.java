package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.ProductEntity;
import hr.brocom.recept.domain.jpa.entity.ProductRecipeEntity;
import hr.brocom.recept.domain.jpa.entity.RecipeEntity;
import hr.brocom.recept.domain.jpa.repository.RecipeRepository;
import hr.brocom.recept.model.ProductDto;
import hr.brocom.recept.model.ProductRecipeDto;
import hr.brocom.recept.model.RecipeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class RecipeJpaImpl {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, RecipeDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addRecipe(RecipeDto recipeDto) {
        modifyRecipe(new RecipeEntity(), recipeDto);
    }

    @Transactional
    public void updateRecipe(RecipeDto recipeDto) {
        modifyRecipe(findByCode(recipeDto.getCode()), recipeDto);
    }

    @Transactional
    public void deactivateRecipe(String code) {
        RecipeEntity recipeEntity = findByCode(code);
        recipeEntity.setActive(false);
        recipeRepository.saveAndFlush(recipeEntity);
    }

    private RecipeEntity findByCode(String code) {
        return recipeRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    private void modifyRecipe(RecipeEntity recipeEntity, RecipeDto recipeDto) {
        recipeEntity.setCode(recipeDto.getCode());
        recipeEntity.setName(recipeDto.getName());
        recipeEntity.setText(recipeDto.getText());
        recipeEntity.setCreatedTime(recipeDto.getCreatedTime());
        recipeEntity.setProductRecipeList(productRecipeConverter(recipeDto.getProductRecipeList()));
        recipeEntity.setRecommendedProducts(productConverter(recipeDto.getRecommendedProducts()));

        System.out.println("ovo" + recipeEntity.getProductRecipeList().get(0));
        recipeRepository.saveAndFlush(recipeEntity);
    }

    private List<ProductRecipeEntity> productRecipeConverter(List<ProductRecipeDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            List<ProductRecipeEntity> collect = dtos.stream().map(productRecipeDto -> modelMapper.map(productRecipeDto, ProductRecipeEntity.class)).collect(Collectors.toList());
            return collect;
        }
    }

    private List<ProductEntity> productConverter(List<ProductDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            return dtos.stream().map(productDto -> modelMapper.map(productDto, ProductEntity.class)).collect(Collectors.toList());
        }
    }
}
