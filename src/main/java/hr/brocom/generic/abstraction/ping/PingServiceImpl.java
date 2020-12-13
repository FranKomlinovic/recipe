package hr.brocom.generic.abstraction.ping;

import hr.brocom.generic.abstraction.service.AbstractCrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PingServiceImpl extends AbstractCrudServiceImpl<Ping> implements PingService {
}
