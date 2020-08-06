package hr.brocom.recept.abstraction.service;

import hr.brocom.recept.SearchCriteria;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Service for CRUD operations.
 *
 * @param <ENTITY> ENTITY type
 * @author fran.komlinovic
 */


public interface AbstractCrudService<ENTITY> extends AbstractBaseService {

    /**
     * Get all ENTITY items from database.
     *
     * @param sort for filtering sorting and paging
     * @return {@link List} of all ENTITY objects
     */
    List<ENTITY> findAll(Sort sort);

    /**
     * Get ENTITY item from database.
     *
     * @param id of requested object.
     * @return ENTITY by id.
     */
    ENTITY findById(UUID id);

    /**
     * Create object in database.
     *
     * @param entity ENTITY object to create
     * @return created ENTITY object
     */
    ENTITY create(ENTITY entity);

    /**
     * Update ENTITY object from database.
     *
     * @param entity ENTITY object to update
     * @return updated ENTITY object
     */
    ENTITY update(ENTITY entity);

    /**
     * Delete object by id.
     *
     * @param id of object to delete
     */
    void delete(UUID id);

    /**
     * Delete list of objects by list of ids.
     *
     * @param idList of objects to delete
     */
    void deleteList(List<UUID> idList);

    ENTITY deactivate(UUID id);

    List<ENTITY> findAllBySearchCriteria(List<SearchCriteria> params);
}
