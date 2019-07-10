package hr.brocom.recept.service;

import hr.brocom.recept.domain.jpa.DelivererJpaImpl;
import hr.brocom.recept.model.DelivererDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelivererService {

    @Autowired
    DelivererJpaImpl delivererJpa;

    public List<DelivererDto> getAllDeliverers() {
        return delivererJpa.getAllDeliverers();
    }

    public void addDeliverer(DelivererDto delivererDto) {
        delivererJpa.addDeliverer(delivererDto);
    }

    public void updateDeliverer(DelivererDto delivererDto) {
        delivererJpa.updateDeliverer(delivererDto);
    }

    public void deactivateDeliverer(String code) {
        delivererJpa.deactivateDeliverer(code);
    }
}
