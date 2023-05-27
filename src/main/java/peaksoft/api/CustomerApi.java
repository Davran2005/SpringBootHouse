package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.enums.Gender;
import peaksoft.enums.HouseType;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.services.AgencyServices;
import peaksoft.services.CustomerServices;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerServices customerServices;
    private final AgencyServices agencyServices;

    @GetMapping
    public String getAllCustomer(Model model) {
        model.addAttribute("getAllCustomer", customerServices.getAllCustomers());
        return "customer/allCustomer";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("gender", new String[]{"MALE", "FEMALE"});
        return "customer/newCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveAgency(@ModelAttribute("newCustomer") Customer customer, @ModelAttribute("gender") String type) throws MyException {
        customer.setGender(Gender.valueOf(type));
        customerServices.saveCustomer(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) throws MyException {
        model.addAttribute("customer", customerServices.getCustomerById(id));
        return "customer/editCustomer";
    }

    @PostMapping("/update/{id}")
    public String saveUpdate(@ModelAttribute("customer") Customer customer,
                             @PathVariable("id") Long id) {
        try {
            customerServices.updateCustomerById(id, customer);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/customer";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            customerServices.deleteCustomerById(id);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/customer";
    }

    @GetMapping("/assign/{id}")
    public String assignCustomerToAgency(@PathVariable Long id, Model model) throws MyException {
        Customer customer = customerServices.getCustomerById(id);
        model.addAttribute("customers", customer);
        model.addAttribute("agency", agencyServices.getAllAgencies());
        return "customer/customerAssign";
    }

    @PostMapping("/saveAssign/{id}")
    public String saveAssign(@RequestParam("agencyName") Long agencyId,
                             @PathVariable Long id) throws MyException {
        customerServices.assignCustomerToAgency(id, agencyId);
        return "redirect:/customer";
    }
}
