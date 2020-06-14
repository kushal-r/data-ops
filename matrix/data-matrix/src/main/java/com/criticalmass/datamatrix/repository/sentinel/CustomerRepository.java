package com.criticalmass.datamatrix.repository.sentinel;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import java.util.UUID;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Configuration
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
