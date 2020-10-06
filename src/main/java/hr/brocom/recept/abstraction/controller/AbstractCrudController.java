package hr.brocom.recept.abstraction.controller;

import hr.brocom.recept.SearchCriteria;
import hr.brocom.recept.abstraction.entity.BaseEntity;
import hr.brocom.recept.abstraction.service.AbstractCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public class AbstractCrudController<ENTITY extends BaseEntity, SERVICE extends AbstractCrudService<ENTITY>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudController.class);

    @Autowired
    protected SERVICE service;

    private final String className;

    protected AbstractCrudController(final Class<ENTITY> type) {
        className = type.getSimpleName();
    }

    @PostMapping(path = "/list")
    public ResponseEntity<List<ENTITY>> findAll(@RequestBody final List<SearchCriteria> params) {
        LOGGER.info("Getting all active {}s...", className);
        final long time = System.currentTimeMillis();
        final List<ENTITY> result = service.findAllBySearchCriteria(params);
        LOGGER.trace("{}.findAllBySearchCriteria() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.info("{}.findAllBySearchCriteria() returned {} results", getServiceName(), result.size());
        return ResponseEntity.ok(result);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<ENTITY> findById(@PathVariable("id") final UUID id) {
        LOGGER.info("Getting {} with UUID: {}...", className, id);
        final long time = System.currentTimeMillis();
        final ENTITY result = service.findById(id);
        LOGGER.trace("{}.findById() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.info("{}.findById() returned {}", getServiceName(), result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ENTITY>> findAllActive() {
        LOGGER.info("Getting all active {}s...", className);
        final long time = System.currentTimeMillis();
        final List<ENTITY> result = service.findAll(Sort.unsorted());
        LOGGER.trace("{}.findAll() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.info("{}.findAll() returned {} results", getServiceName(), result.size());
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<ENTITY> create(@RequestBody @Valid final ENTITY entity) {
        LOGGER.info("Creating {}...", entity);
        final long time = System.currentTimeMillis();
        final ENTITY created = service.create(entity);
        LOGGER.trace("{}.create() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.info("{}.create() returned {}", getServiceName(), created);
        return ResponseEntity.ok(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ENTITY> update(@RequestBody final ENTITY entity) {
        LOGGER.info("Updating {} with UUID: {}...", className, entity.getId());
        final long time = System.currentTimeMillis();
        final ENTITY update = service.update(entity);
        LOGGER.trace("{}.update() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.info("{}.update() returned {}", getServiceName(), update);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final UUID id) {
        LOGGER.info("Deleting {}: with UUID: {}...", className, id);
        final long time = System.currentTimeMillis();
        service.delete(id);
        LOGGER.trace("{}.delete() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.trace("{} deleted successfully", className);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ENTITY> deactivate(@PathVariable final UUID id) {
        LOGGER.info("Deactivating {}: with UUID: {}...", className, id);
        final long time = System.currentTimeMillis();
        final ENTITY deactivate = service.deactivate(id);
        LOGGER.trace("{}.deactivate() finished in {} ms", getServiceName(), System.currentTimeMillis() - time);
        LOGGER.trace("{} deactivated successfully", className);
        return ResponseEntity.ok(deactivate);
    }

    private String getServiceName() {
        return service.getClass().getSimpleName();
    }
}
