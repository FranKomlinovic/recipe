package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.entity.DelivererEntity;
import hr.brocom.recept.domain.jpa.repository.DelivererRepository;
import hr.brocom.recept.model.DelivererDto;
import hr.brocom.recept.model.RestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelivererControllerTest {

    private static final String CODE       = "DEL1";
    private static final String FIRST_NAME = "Pero";
    private static final String LAST_NAME  = "Peric";

    private static final String INIT_CODE       = "DEL0";
    private static final String INIT_FIRST_NAME = "Ivan";
    private static final String INIT_LAST_NAME  = "Ivanic";

    @Autowired
    DelivererController delivererController;

    @Autowired
    DelivererRepository delivererRepository;

    @Test
    @Transactional
    public void getAllActiveDeliverers() {
        RestDto<List<DelivererDto>> response = delivererController.getAllActiveDeliverers();
        assertNotNull(response.getData());
        assertTrue(response.isSuccess());
        Optional<DelivererDto> delivererOptional = response.getData()
                                                           .stream()
                                                           .filter(delivererDto -> INIT_CODE.equals(delivererDto.getCode()))
                                                           .findFirst();

        assertTrue(delivererOptional.isPresent());
        DelivererDto delivererDto = delivererOptional.get();

        assertEquals(INIT_CODE, delivererDto.getCode());
        assertEquals(INIT_FIRST_NAME, delivererDto.getFirst_name());
        assertEquals(INIT_LAST_NAME, delivererDto.getLast_name());
    }

    @Test
    @Transactional
    public void addDeliverer() {
        delivererController.addDeliverer(createInitDeliverer());
        DelivererEntity delivererFromDatabase = findDelivererByCode(CODE);

        assertEquals(CODE, delivererFromDatabase.getCode());
        assertEquals(FIRST_NAME, delivererFromDatabase.getFirst_name());
        assertEquals(LAST_NAME, delivererFromDatabase.getLast_name());
    }

    private DelivererDto createInitDeliverer() {
        DelivererDto delivererDto = new DelivererDto();
        delivererDto.setCode(CODE);
        delivererDto.setFirst_name(FIRST_NAME);
        delivererDto.setLast_name(LAST_NAME);
        return delivererDto;
    }

    private DelivererEntity findDelivererByCode(String code) {
        return delivererRepository.findByCode(code)
                                  .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    @Test
    @Transactional
    public void updateDeliverer() {
        // Init data
        DelivererDto initDeliverer = createInitDeliverer();

        // Check if not exists yet
        assertFalse(delivererRepository.findByCode(CODE)
                                       .isPresent());

        // Check if values from init are present
        Optional<DelivererEntity> entityFromInit = delivererRepository.findByCode(INIT_CODE);
        assertTrue(entityFromInit.isPresent());
        DelivererEntity deliverersEntity = entityFromInit.get();

        // Assert if everything is as in import script
        assertEquals(true, deliverersEntity.getActive());
        assertEquals(INIT_CODE, deliverersEntity.getCode());
        assertEquals(INIT_FIRST_NAME, deliverersEntity.getFirst_name());
        assertEquals(INIT_LAST_NAME, deliverersEntity.getLast_name());

        // Change information from import script to information from initDeliverer method by changing code
        initDeliverer.setCode(INIT_CODE);

        // Execute update
        delivererController.updateDeliverer(initDeliverer);

        DelivererEntity updatedDeliverer = findDelivererByCode(INIT_CODE);

        assertEquals(true, updatedDeliverer.getActive());
        assertEquals(FIRST_NAME, updatedDeliverer.getFirst_name());
        assertEquals(LAST_NAME, updatedDeliverer.getLast_name());
    }

    @Test
    @Transactional
    public void deactivateDeliverer() {
        DelivererEntity delivererByCode = findDelivererByCode(INIT_CODE);

        assertTrue(delivererByCode.getActive());

        delivererController.deactivateDeliverer(INIT_CODE);

        delivererByCode = findDelivererByCode(INIT_CODE);

        assertFalse(delivererByCode.getActive());
    }
}