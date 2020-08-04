package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.SearchCriteria;
import hr.brocom.recept.domain.jpa.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDaoImpl<ENTITY extends BaseEntity> implements AbstractDao<ENTITY> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class type;

    public AbstractDaoImpl(Class type) {
        this.type = type;
    }

    @Override
    public List<ENTITY> search(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = builder.createQuery(type);
        Root r = query.from(type);

        Predicate predicate = builder.conjunction();

        AbstractSearchQueryCriteriaConsumer searchConsumer =
                new AbstractSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(ENTITY entity) {
        entityManager.persist(entity);
    }
}
