package hr.brocom.generic.abstraction.ping;

import hr.brocom.generic.abstraction.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController extends AbstractCrudController<Ping, PingService> {
    protected PingController() {
        super(Ping.class);
    }
}