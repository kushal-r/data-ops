package com.criticalmass.datamatrix.helper.converter.mapper;

import com.criticalmass.datamatrix.dto.sentinel.CustomerDTO;
import com.criticalmass.datamatrix.entity.sentinel.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Date: 19/06/20
 *
 * @author Kushal Roy
 */
@Component
public class CustomerConverter implements IEntityConverter<CustomerDTO, Customer> {

  @Autowired
  private ModelMapper modelMapper;


  @Override
  public Customer map(CustomerDTO source) {
    return modelMapper.map(source, Customer.class);
  }

  @Override
  public CustomerDTO map(Customer target) {
    return modelMapper.map(target, CustomerDTO.class);
  }
}
