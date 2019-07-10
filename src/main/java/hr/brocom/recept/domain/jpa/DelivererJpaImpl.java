package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.DelivererEntity;
import hr.brocom.recept.domain.jpa.repository.DelivererRepository;
import hr.brocom.recept.model.DelivererDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class DelivererJpaImpl {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DelivererRepository delivererRepository;

    @Transactional
    public List<DelivererDto> getAllDeliverers() {
        return delivererRepository.findAll()
                                  .stream()
                                  .map(r -> modelMapper.map(r, DelivererDto.class))
                                  .collect(Collectors.toList());
    }

    @Transactional
    public void addDeliverer(DelivererDto delivererDto) {
        delivererRepository.saveAndFlush(modelMapper.map(delivererDto, DelivererEntity.class));
    }

    @Transactional
    public DelivererDto findDelivererByCode(String code) {
        return modelMapper.map(findByCode(code), DelivererDto.class);
    }

    private DelivererEntity findByCode(String code) {
        return delivererRepository.findByCode(code)
                                  .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }
}
