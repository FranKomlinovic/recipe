package hr.brocom.recept.model.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Genericka klasa za pretvaranje iz jednog u drugi objekt.
 * Potrebno je predati Converter preko setConverter metode ili
 * u konstruktoru klase koja extenda ovu klasu.
 *
 * @param <E> Entity
 * @param <D> DTO
 */
public abstract class DataMapper<E, D> {

    private final ModelMapper mm;

    private final Class<E> entityClass;

    private final Class<D> dtoClass;

    public DataMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.mm = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.mm.getConfiguration()
               .setFieldMatchingEnabled(true)
               .setImplicitMappingEnabled(true)
               .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
               .setMatchingStrategy(MatchingStrategies.STRICT);

        // If entity converter exists, add it to model mapper
        Converter<E, D> edConverter = addDtoConverter();
        if (edConverter != null) {
            this.mm.addConverter(edConverter);
        }

        // If dto converter exists, add it to model mapper
        Converter<D, E> deConverter = addEntityConverter();
        if (deConverter != null) {
            this.mm.addConverter(deConverter);
        }
    }

    protected abstract Converter<E, D> addDtoConverter();

    protected abstract Converter<D, E> addEntityConverter();

    /**
     * Iterira kroz listu i converta elemente
     *
     * @param data lista entiteta
     * @return lista Dto
     */
    public List<D> convertToDto(List<E> data) {
        if (data == null) {
            return null;
        }
        return data.stream()
                   .map(this::convertToDto)
                   .collect(Collectors.toList());
    }

    /**
     * Pretvara entity objekte u dto-objekte.
     *
     * @param data podaci u entity-u
     * @return podaci u dto-u
     */
    public D convertToDto(E data) {
        try {
            return mm.map(data, dtoClass);
        } catch (Exception e) {
            //TODO: Napraviti exceptione i staviti server
            //            throw new ServerException(ApiErrorCode.ERROR_CONVERTING_DATA,
            //                                      data.getClass()
            //                                          .getName(),
            //                                      e);
            throw new RuntimeException();
        }
    }

    /**
     * Iterira kroz set i converta elemente
     *
     * @param data set entiteta
     * @return set Dto
     */
    public Set<D> convertToDto(Set<E> data) {
        if (data == null) {
            return null;
        }
        return data.stream()
                   .map(this::convertToDto)
                   .collect(Collectors.toSet());
    }

    /**
     * Iterira kroz listu i converta elemente
     *
     * @param data lista Dto
     * @return lista entiteta
     */
    public List<E> convertToEntity(List<D> data) {
        if (data == null) {
            return null;
        }
        return data.stream()
                   .map(this::convertToEntity)
                   .collect(Collectors.toList());
    }

    /**
     * Pretvara dto objekte u entity objekte.
     *
     * @param data podaci u dto-u
     * @return podaci u entity-u
     */
    public E convertToEntity(D data) {
        try {
            return mm.map(data, entityClass);
        } catch (Exception e) {
            //TODO: Napraviti exceptione i staviti server
            //            throw new ServerException(ApiErrorCode.ERROR_CONVERTING_DATA,
            //                                      data.getClass()
            //                                          .getName(),
            //                                      e);
            throw new RuntimeException();
        }
    }

    /**
     * Iterira kroz set i converta elemente
     *
     * @param data set Dto
     * @return set entiteta
     */
    public Set<E> convertToEntity(Set<D> data) {
        if (data == null) {
            return null;
        }
        return data.stream()
                   .map(this::convertToEntity)
                   .collect(Collectors.toSet());
    }

    protected ModelMapper getMm() {
        return mm;
    }
}
