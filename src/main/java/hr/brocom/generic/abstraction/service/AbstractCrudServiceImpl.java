package hr.brocom.generic.abstraction.service;

import hr.brocom.generic.SearchCriteria;
import hr.brocom.generic.abstraction.repository.AbstractRepository;
import hr.brocom.generic.abstraction.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Abstract service for CRUD methods.
 *
 * @param <ENTITY> ENTITY object type
 * @author fran.komlinovic (serengeti)
 */
public abstract class AbstractCrudServiceImpl<ENTITY extends BaseEntity> implements AbstractCrudService<ENTITY> {


    @Autowired
    protected AbstractRepository<ENTITY> abstractRepositoryImpl;

    @Override
    public List<ENTITY> findAll(final Sort sort) {
        return abstractRepositoryImpl.findAll(sort);
    }

    @Override
    public ENTITY findById(final UUID id) {
        return abstractRepositoryImpl.findById(id);
    }

    @Override
    public ENTITY create(final ENTITY dto) {
        return abstractRepositoryImpl.create(dto);
    }

    @Override
    public ENTITY update(final ENTITY dto) {
        return abstractRepositoryImpl.update(dto);
    }

    @Override
    public void delete(final UUID id) {
        abstractRepositoryImpl.delete(id);
    }

    @Override
    public void deleteList(final List<UUID> id) {
        abstractRepositoryImpl.deleteList(id);
    }

    @Override
    public ENTITY deactivate(final UUID id) {
        return abstractRepositoryImpl.deactivate(id);
    }

    @Override
    public List<ENTITY> findAllBySearchCriteria(final List<SearchCriteria> params) {
        return abstractRepositoryImpl.search(params);
    }
}
