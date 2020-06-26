package com.criticalmass.datamatrix.controller;

import com.criticalmass.datamatrix.dto.sentinel.CustomerDTO;
import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.helper.converter.mapper.IEntityConverter;
import com.criticalmass.datamatrix.service.sentinel.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@RestController
@RequestMapping(path = "/v1")
@Api(tags = "Sentinel management api")
public class SentinelController {

  /* ---------------- Fields ---------------- */

  @Autowired
  private CustomerService customerService;

  @Autowired
  private IEntityConverter<CustomerDTO, Customer> customerConverter;


  /* ---------------- Public api ---------------- */

  @ApiOperation(value = "Add a customer.", notes = "Adds new customer to the application",
      response = Customer.class, produces = "application/json", consumes = "application/json",
      tags = "Customer")
  @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> createNewCustomer(@RequestBody CustomerDTO customerDTO) {

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(customerService.createNewCustomer(customerConverter.map(customerDTO)));
  }


  @ApiOperation(value = "Find a customer.", notes = "Finds customer with the given ID.",
      responseContainer = "List", response = Customer.class, produces = "application/json",
      consumes =
          "application/json",
      tags = "Customer")
  @GetMapping(value = "/customer/{id}", produces = "application/json")
  public ResponseEntity<?> findCustomerById(@ApiParam(value = "ID of the customer or black to "
      + "fetch list of all customers")
  @PathVariable(value = "id", required = false) String id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(customerService.findCustomer(id).parallelStream().map(x -> customerConverter.map(x))
            .collect(
                Collectors.toList()));
  }


}
