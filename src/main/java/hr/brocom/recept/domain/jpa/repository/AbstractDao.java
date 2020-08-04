package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbstractDao<ENTITY> {
    List<ENTITY> search(List<SearchCriteria> params);

    void save(ENTITY entity);
}
