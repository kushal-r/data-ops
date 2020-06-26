package com.criticalmass.datamatrix.service.sentinel.impl;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import com.criticalmass.datamatrix.service.sentinel.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    Customer customer1 = new Customer();
    customer1.setUsername("test_name1");
    customer1.setId(UUID.fromString("12345678-1234-5678-1234-567812345678"));
    Customer customer2 = new Customer();
    customer2.setUsername("test_name2");
    customer2.setId(UUID.fromString("12345678-1234-5678-1234-567812345699"));

    List<Customer> customerList = new ArrayList<>();
    customerList.add(customer1);
    customerList.add(customer2);

    Mockito.when(customerRepository.findById(customer1.getId())).thenReturn(
        Optional.of(customer1));

    Mockito.when(customerRepository.findAll(Sort.by(Direction.ASC, "username")))
        .thenReturn(customerList);

  }

  /* ---------------- Test cases ---------------- */

  @Test
  public void whenValidId_thenCustomerShouldBeFound() {

    List<Customer> customer = customerService.findCustomer("12345678-1234-5678-1234-567812345678");
    Assertions.assertTrue(customer.get(0).getUsername().equalsIgnoreCase("test_name1"));
  }

  @Test
  public void whenBlankId_thenReturnAllCustomers() {
    Customer customer1 = new Customer();
    customer1.setUsername("test_name1");
    customer1.setId(UUID.fromString("12345678-1234-5678-1234-567812345678"));

    List<Customer> customers = customerService.findCustomer(null);
    Assertions.assertTrue(customers.size() == 2);
    Assertions.assertTrue(customers.get(0).equals(customer1));
  }


}
