package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.repository.RecipeRepository;
import hr.brocom.recept.model.RecipeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
