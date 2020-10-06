package hr.brocom.generic.abstraction.repository;

import hr.brocom.generic.SearchCriteria;
import hr.brocom.generic.abstraction.AbstractSearchQueryCriteriaConsumer;
import hr.brocom.generic.abstraction.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public abstract class AbstractRepositoryImpl<ENTITY extends BaseEntity, DAO extends JpaRepository<ENTITY, UUID>> implements AbstractRepository<ENTITY> {

    @Autowired
    protected DAO dao;

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class type;

    public AbstractRepositoryImpl(final Class type) {
        this.type = type;
    }

    @Override
    public List<ENTITY> search(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ENTITY> query = builder.createQuery(type);
        final Root r = query.from(type);

        Predicate predicate = builder.conjunction();

        final AbstractSearchQueryCriteriaConsumer searchConsumer =
                new AbstractSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

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

}
