package com.example.bookshop.dao;

import com.example.bookshop.dto.CustomerOrder;
import com.example.bookshop.entity.Customer;
import com.example.bookshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    Optional<Customer> findCustomerByCustomerName(String customerName);
    @Query("""
select o from Order o where o.customer.customerName = ?1
""")
    List<Order> fetchOrderByCustomerName(String customerName);
    @Query("""
select new com.example.bookshop.dto.CustomerOrder(c.customerName,c.email,c.phoneNumber,o.billingAddress,o.shippingAddress)
 from Customer c join c.orders o where c.customerName = ?1
""")
    Optional<CustomerOrder> customerOrderInfo(String customerName);
}
