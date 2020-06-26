package com.criticalmass.datamatrix.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.criticalmass.datamatrix.dto.sentinel.CustomerDTO;
import com.criticalmass.datamatrix.entity.sentinel.Customer;
import com.criticalmass.datamatrix.helper.converter.mapper.IEntityConverter;
import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import com.criticalmass.datamatrix.service.sentinel.CustomerService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Date: 18/06/20
 *
 * @author Kushal Roy
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(SentinelController.class)
public class SentinelControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @MockBean
  private CustomerRepository customerRepository;

  @MockBean
  private IEntityConverter<CustomerDTO, Customer> customerConverter;

  @BeforeEach
  public void setUp() throws Exception {
  }


  /* ---------------- Test cases ---------------- */

  @Test
  public void whenValidId_GetJSONArray() throws Exception {
    Customer customer = new Customer();
    customer.setId(UUID.fromString("12345678-1234-5678-1234-567812345678"));
    customer.setUsername("test");
    List<Customer> customerList = new ArrayList<>();
    customerList.add(customer);

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setContactNumber("+91 1234567890");
    customerDTO.setDateOfBirth(LocalDate.of(1988, 10, 29));
    customerDTO.setEmailId("test@gmail.com");
    customerDTO.setUsername("test");

    given(customerConverter.map(customer)).willReturn(customerDTO);

    given(customerService.findCustomer("12345678-1234-5678-1234-567812345678"))
        .willReturn(customerList);

    mockMvc.perform(get("/v1/customer/12345678-1234-5678-1234-567812345678")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].username", is(customerDTO.getUsername())));

    verify(customerService, VerificationModeFactory.times(1))
        .findCustomer(customer.getId().toString());
    reset(customerService);
  }

}
