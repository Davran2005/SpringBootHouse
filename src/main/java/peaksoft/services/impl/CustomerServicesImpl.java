package peaksoft.services.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.enums.Gender;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.model.Customer;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.CustomerRepository;
import peaksoft.services.AgencyServices;
import peaksoft.services.CustomerServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {
    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyServices;

    @Override
    public void saveCustomer(Customer customer) {
//        if (!customer.getFirstName().isBlank()) {
//            if (!customer.getEmail().isBlank() || customer.getEmail().contains("@gmail.com")) {
//                if (customer.getAge() > 0) {
//                    if (customer.getPhoneNumber().startsWith("+996") || customer.getPhoneNumber().length() == 13) {
//                        if (customer.getDataOfBirth().isAfter(LocalDate.now())) {
//                            customerRepository.save(customer);
//                        } else {
//                            try {
//                                throw new MyException("can't give a future number");
//                            } catch (MyException e) {
//                                System.err.println(e.getMessage());
//                            }
//                        }
//                    } else {
//                        try {
//                            throw new MyException("there is no age 0");
//                        } catch (MyException e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                }
//            } else {
//                try {
//                    throw new MyException("Customer email contains @gmail.com");
//                } catch (MyException e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//        } else {
//            try {
//                throw new MyException("Customer name is blank");
//            } catch (MyException e) {
//                System.err.println(e.getMessage());
//            }
//        }
        customerRepository.save(customer);
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) throws MyException {
        return customerRepository.findById(id).orElseThrow(() ->
                new MyException("Customer by id: " + id + " is not found!!!"));
    }


    @Override
    public void updateCustomerById(Long id, Customer customer) throws MyException {
        Customer customer1 = customerRepository.findById(id).orElseThrow(() -> new MyException("Customer by id: " + id + " is not found!!!"));
//        if (!customer.getFirstName().isBlank()) {
//            customer1.setFirstName(customer.getFirstName());
//            if (!customer.getLastName().isBlank()) {
//                customer1.setLastName(customer.getLastName());
//                if (!customer.getEmail().isBlank() || customer.getEmail().contains("@gmail.com")) {
//                    customer1.setEmail(customer.getEmail());
//                    if (customer.getGender() != null) {
//                        customer1.setGender(customer.getGender());
//                        if (customer.getAge() > 0) {
//                            customer1.setAge(customer.getAge());
//                            if (customer.getPhoneNumber().startsWith("+996") || customer.getPhoneNumber().length() == 13) {
//                                customer1.setPhoneNumber(customer.getPhoneNumber());
//                                if (customer.getDataOfBirth().isAfter(LocalDate.now())) {
//                                    customer1.setDataOfBirth(customer.getDataOfBirth());
//                                }
//                                customerRepository.save(customer);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        customer.setId(customer1.getId());
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) throws MyException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new MyException("Customer by id: " + id + " is not found!!!"));
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> searchCustomer(String word) {
        return null;
    }

    @Override
    public void assignCustomerToAgency(Long customerId, Long agencyId) throws MyException {
        System.out.println(customerId);
        System.out.println(agencyId);
        Customer customer = customerRepository.findById(customerId).get();
        Agency agency = agencyServices.findById(agencyId).get();
            customer.getAgencies().add(agency);
            agency.getCostumers().add(customer);

        agencyServices.save(agency);
    }
}
