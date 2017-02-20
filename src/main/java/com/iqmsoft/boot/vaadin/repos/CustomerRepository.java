package com.iqmsoft.boot.vaadin.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iqmsoft.boot.vaadin.domain.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where UPPER(c.firstName) like %:searchTerm% or UPPER(c.lastName) like UPPER(%:searchTerm%) order by lastName, firstName")
    Iterable<Customer> findNamesLike(@Param("searchTerm") String searchTerm);

}
