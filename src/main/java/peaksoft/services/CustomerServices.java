package peaksoft.services;

import peaksoft.exception.MyException;
import peaksoft.model.Customer;

import java.util.List;

public interface CustomerServices {
    void saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id) throws MyException;
    void updateCustomerById(Long id, Customer customer) throws MyException;
    void deleteCustomerById(Long id) throws MyException;
    List<Customer> searchCustomer(String word);
    void assignCustomerToAgency(Long customerId, Long agencyId) throws MyException;
}
