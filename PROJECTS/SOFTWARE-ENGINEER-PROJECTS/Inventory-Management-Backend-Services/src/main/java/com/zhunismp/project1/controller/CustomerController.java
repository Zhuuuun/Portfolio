package com.zhunismp.project1.controller;

import com.zhunismp.project1.entity.Customer;
import com.zhunismp.project1.exception.CustomerNotFoundException;
import com.zhunismp.project1.response.ResponseHandler;
import com.zhunismp.project1.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                customerService.findAll()
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable int customerId) {
        // get current method name : https://stackoverflow.com/questions/442747/getting-the-name-of-the-currently-executing-method
        if(!customerService.existsById(customerId)) throw new CustomerNotFoundException("Customer Doesn't exists with id : " + customerId);
        Customer customer = customerService.findById(customerId);

        return ResponseHandler.responseBuilder(
                    "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                    HttpStatus.OK,
                    customerService.findById(customerId)
        );

    }

    @PostMapping("/customer/add")
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid Customer customer) {
        Customer buff = customerService.save(customer);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.CREATED,
                buff
        );
    }

    @DeleteMapping("customer/delete/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int customerId) {
        if(!customerService.existsById(customerId))  throw new CustomerNotFoundException("Customer Doesn't exists with id : " + customerId);
        customerService.deleteById(customerId);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                null
        );
    }

    @PutMapping("customer/update")
    public ResponseEntity<Object> updateCustomer(@RequestBody @Valid Customer customer) {
        if (!customerService.existsById(customer.getId())) throw new CustomerNotFoundException("Customer Doesn't exists with id : " + customer.getId());
        Customer buff = customerService.save(customer);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                buff
        );
    }

}
