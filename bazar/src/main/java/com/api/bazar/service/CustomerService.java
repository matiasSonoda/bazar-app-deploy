
package com.api.bazar.service;

import com.api.bazar.entity.Customer;
import com.api.bazar.entity.dto.CustomerDto;
import com.api.bazar.exception.CustomerNotFoundException;
import com.api.bazar.exception.DuplicateResourceException;
import com.api.bazar.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerDto saveCustomer(CustomerDto dto){
        Customer request = new Customer();
        if (dto.getIdCustomer() != null){
            request.setIdCustomer(dto.getIdCustomer());
        }
        if(customerRepository.existsByDni(dto.getDni())){
            throw new DuplicateResourceException("The DNI already exists"); 
        }
        request.setDni(dto.getDni() );
        request.setName(dto.getName());
        request.setLastName(dto.getLastName());
        Customer aux = customerRepository.save(request);
        CustomerDto response = new CustomerDto();
        response.setIdCustomer(aux.getIdCustomer());
        response.setDni(aux.getDni());
        response.setName(aux.getName());
        response.setLastName(aux.getLastName());
        return response;
    }
    
    public List<CustomerDto> getAllCustomer(){
       List<Customer> customers = customerRepository.findAll();
       List<CustomerDto> dto = new ArrayList<>();
       
       dto = customers.stream().map((custom)->{
                if(custom.getDni() == null || custom.getLastName() == null || custom.getName() == null || custom.getSales() == null){
                    throw new RuntimeException("Missing fields in customer with ID" + custom.getIdCustomer());
                }
                CustomerDto aux = new CustomerDto();
                aux.setDni(custom.getDni());
                aux.setIdCustomer(custom.getIdCustomer());
                aux.setName(custom.getName());
                aux.setLastName(custom.getLastName());
                return aux;
       }).collect(Collectors.toList());
       
       return dto;
    }
    
    public CustomerDto getCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException());
        
            CustomerDto dto = new CustomerDto();
            dto.setIdCustomer(customer.getIdCustomer());
            dto.setDni(customer.getDni());
            dto.setName(customer.getName());
            dto.setLastName(customer.getLastName());
            return dto;
        
    }
    
    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new CustomerNotFoundException();
        }
        customerRepository.deleteById(id);
    }
    
    public CustomerDto updateCustomer(Long id, CustomerDto request){
        if(request.getIdCustomer() == null || !id.equals(request.getIdCustomer())){
            throw new IllegalArgumentException("Invalid credentials");
        }
        if (!customerRepository.existsById(id)){
            throw new CustomerNotFoundException();
        }
        return saveCustomer(request);
    }
}
