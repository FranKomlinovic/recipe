package hr.brocom.generic.abstraction.repository;

import hr.brocom.generic.SearchCriteria;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface AbstractRepository<ENTITY> {

    List<ENTITY> findAll(Sort sort);

    ENTITY findById(UUID id);

    ENTITY create(ENTITY dto);

    ENTITY update(ENTITY dto);

    void delete(UUID id);

    void deleteList(List<UUID> id);

    ENTITY deactivate(UUID id);

    List<ENTITY> search(List<SearchCriteria> params);

}
