package com.criticalmass.datamatrix.service.sentinel.impl;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import com.criticalmass.datamatrix.service.sentinel.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.annotation.Resource;
import javax.validation.constraints.Email;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Service
@Transactional("sentinelTransactionManager")
public class CustomerServiceImpl implements CustomerService {

  /* ---------------- Fields ---------------- */
  private static final Logger log = LogManager.getLogger();

  @Resource
  private CustomerRepository customerRepository;

  /* ---------------- Methods ---------------- */

  @Validated
  public Customer getCustomerByEmail(@Email String emailId) {
    log.info("Find customer whose email id is : {}", emailId);

    Customer probe = new Customer();
    probe.setEmailId(emailId);
    return customerRepository.findOne(Example.of(probe))
        .orElseThrow(() -> new NoSuchElementException("Customer with email " + emailId
            + " not found in DB"));
  }

  @Override
  public Customer createNewCustomer(Customer customer) {
    log.info("Add new customer with email {}", customer);
    Customer newCustomer = customerRepository.save(customer);

    return newCustomer;
  }


  @Override
  public List<Customer> findCustomer(String id) {
    if (StringUtils.isBlank(id)) {
      log.info("Find all customers");
      /*Page<Customer> page = customerRepository
          .findAll(PageRequest.of(0, 10, Sort.by(Direction.ASC,
              "username")));
      return page.toList();*/
      return customerRepository.findAll(Sort.by(Direction.ASC, "username"));
    }

    log.info("Find customer whose id is : {}", id);
    List<Customer> customerList = new ArrayList<>();
    customerList.add(customerRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new NoSuchElementException("Customer with ID " + id
            + " not present in DB")));
    return customerList;
  }
}
