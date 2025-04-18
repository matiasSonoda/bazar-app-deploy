
package com.api.bazar.repository;

import com.api.bazar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
   boolean existsByDni(String dni);

}
