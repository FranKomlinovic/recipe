package hr.brocom.recept.controller;

import hr.brocom.recept.model.DelivererDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.DelivererService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliverer")
public class DelivererController {

    private static final Logger log = LoggerFactory.getLogger(DelivererController.class);

    @Autowired
    DelivererService delivererService;

    @GetMapping("/list")
    public RestDto<List<DelivererDto>> getAllActiveDeliverers() {
        log.trace("Get all active deliverers");
        long time = System.currentTimeMillis();
        List<DelivererDto> deliverers = delivererService.getAllDeliverers();
        log.trace("getAllActiveDeliverers finished in {} ms", System.currentTimeMillis() - time);
        log.trace("getAllActiveDeliverers returned {} results", deliverers.size());
        return RestDto.success(deliverers);
    }

    @PostMapping("")
    public RestDto<Void> addDeliverer(@RequestBody @Valid DelivererDto delivererDto) {
        log.info("Adding deliverer: {}...", delivererDto.getCode());
        log.trace("Deliverer: {}", delivererDto);
        long time = System.currentTimeMillis();
        delivererService.addDeliverer(delivererDto);
        log.trace("addDeliverer finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Deliverer uspješno dodan!");
    }

    @PutMapping("")
    public RestDto<Void> updateDeliverer(@RequestBody DelivererDto delivererDto) {
        log.info("Update deliverer: {}...", delivererDto.getCode());
        log.trace("Deliverer: " + delivererDto);
        long time = System.currentTimeMillis();
        delivererService.updateDeliverer(delivererDto);
        log.trace("updateDeliverer finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Deliverer uspješno ažuriran");
    }

    @DeleteMapping("/{code}")
    public RestDto<Void> deactivateDeliverer(@PathVariable String code) {
        log.info("Deleting deliverer: {}...", code);
        long time = System.currentTimeMillis();
        delivererService.deactivateDeliverer(code);
        log.trace("deleteDeliverer finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Deliverer uspješno obrisan");
    }
}
