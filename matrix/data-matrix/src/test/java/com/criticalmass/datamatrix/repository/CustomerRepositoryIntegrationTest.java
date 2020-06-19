package com.criticalmass.datamatrix.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaAuditing
public class CustomerRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private CustomerRepository customerRepository;

  /* ---------------- Test cases ---------------- */

  @Test
  public void whenFindByUsername_thenReturnCustomer() {
    Customer customer = new Customer();
    customer.setActive(true);
    customer.setEmail("customerA@test.com");
    customer.setUsername("customerA");
    customer.setPassword("password");

    //Save this in mock DB
    testEntityManager.persist(customer);
    Customer probe = new Customer();
    probe.setUsername("customerA");
    probe.setActive(true);

    Customer fetchedCustomer = customerRepository.findOne(Example.of(probe)).get();
    assertThat(fetchedCustomer.getUsername()).isEqualTo(customer.getUsername());
  }
}
