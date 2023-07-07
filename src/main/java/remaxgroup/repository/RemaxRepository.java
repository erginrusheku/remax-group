package remaxgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remaxgroup.model.Remax;

public interface RemaxRepository extends JpaRepository<Remax, Long> {
}
