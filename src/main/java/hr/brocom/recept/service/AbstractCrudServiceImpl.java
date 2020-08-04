package hr.brocom.recept.service;

import hr.brocom.recept.SearchCriteria;
import hr.brocom.recept.domain.jpa.entity.BaseEntity;
import hr.brocom.recept.domain.jpa.repository.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Abstract service for CRUD methods.
 *
 * @param <ENTITY> ENTITY object type
 * @param <DAO>    DAO object type
 * @author fran.komlinovic (serengeti)
 */
public abstract class AbstractCrudServiceImpl<ENTITY extends BaseEntity, DAO extends JpaRepository<ENTITY, UUID>> implements AbstractCrudService<ENTITY> {

    /**
     * Autowired {@link DAO} repository.
     */
    @Autowired
    protected DAO dao;

    @Autowired
    protected AbstractDao<ENTITY> abstractDao;

    @Override
    public List<ENTITY> findAll(final Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public ENTITY findById(final UUID id) {
        return dao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ENTITY create(final ENTITY dto) {
        return dao.saveAndFlush(dto);
    }

    @Override
    public ENTITY update(final ENTITY dto) {
        findById(dto.getId());
        return dao.saveAndFlush(dto);
    }

    @Override
    public void delete(final UUID id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteList(final List<UUID> id) {
        id.forEach(uuid -> dao.deleteById(uuid));
    }

    @Override
    public ENTITY deactivate(final UUID id) {
        final ENTITY entity = dao.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setActive(false);
        return dao.saveAndFlush(entity);
    }

    @Override
    public List<ENTITY> searchUser(List<SearchCriteria> params) {
        return abstractDao.search(params);
    }
}
