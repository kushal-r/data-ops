package com.criticalmass.datamatrix.service.sentinel;

import com.criticalmass.datamatrix.entity.sentinel.Customer;
import java.util.List;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
public interface CustomerService {

  Customer createNewCustomer(Customer customer);

  List<Customer> findCustomer(String id);

}
