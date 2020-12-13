package hr.brocom.generic.abstraction.ping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PingRepository extends JpaRepository<Ping, Long> {
}
