package ehb.alvi.afbackendapi.dao;

import ehb.alvi.afbackendapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
