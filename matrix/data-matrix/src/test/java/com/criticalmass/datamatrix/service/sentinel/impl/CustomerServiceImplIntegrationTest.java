package com.criticalmass.datamatrix.service.sentinel.impl;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import com.criticalmass.datamatrix.service.sentinel.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Date: 17/06/20
 *
 * @author Kushal Roy
 */
@ExtendWith(SpringExtension.class)
public class CustomerServiceImplIntegrationTest {

  @Autowired
  private CustomerService customerService;

  @MockBean
  private CustomerRepository customerRepository;

  @TestConfiguration
  static class CustomerServiceImplTestContextConfiguration {

    @Bean
    public CustomerService customerService() {
      return new CustomerServiceImpl();
    }
  }

  @BeforeEach
  public void setUp() {
    Customer customer = new Customer();
    customer.setUsername("test_name");
    customer.setId(UUID.fromString("12345678-1234-5678-1234-567812345678"));
    List<Customer> customerList = new ArrayList<>();
    customerList.add(customer);

    Mockito.when(customerRepository.findById(customer.getId())).thenReturn(
        Optional.of(customer));
  }

  /* ---------------- Test cases ---------------- */

  @Test
  public void whenValidId_thenCustomerShouldBeFound() {

    List<Customer> customer = customerService.findCustomer("12345678-1234-5678-1234-567812345678");
    Assertions.assertThat(customer.get(0).getUsername().equalsIgnoreCase("test_name"));
  }


}
