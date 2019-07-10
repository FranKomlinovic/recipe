package hr.brocom.recept.controller;

import hr.brocom.recept.model.DelivererDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.DelivererService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
