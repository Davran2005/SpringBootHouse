package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.exception.MyException;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.services.BookingService;
import peaksoft.services.CustomerServices;
import peaksoft.services.HouseServices;
@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingApi {
    private final BookingService service;
    private final HouseServices houseService;
    private final CustomerServices customerService;

    @GetMapping
    public String getAllBooking(Model model) {
        model.addAttribute("bookings", service.getAllBooking());
        return "/book/booking";
    }

    @GetMapping("/newBookings")
    public String createHouse(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("houses", houseService.getAllHouses());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "/book/newBooking";
    }

    @PostMapping("/saveBooking")
    public String saveHouse(@ModelAttribute("newBooking") Booking booking,
                            @RequestParam("house.id") Long houseId,
                            @RequestParam("customer.id") Long customerId) throws MyException {
        House house = houseService.getHouseById(houseId);
        Customer customer = customerService.getCustomerById(customerId);
        booking.setHouse(house);
        booking.setCustomer(customer);
        service.saveBooking(booking);
        return "redirect:/bookings";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyException {
        service.deleteBookingById(id);
        return "redirect:/bookings";
    }
    @GetMapping("/update/{id}")
    public String updateBooking(@PathVariable Long id, Model model) throws MyException {
        Booking booking = service.getBookingById(id);
        model.addAttribute("booking",booking);
        model.addAttribute("house",houseService.getAllHouses());
        model.addAttribute("customers",customerService.getAllCustomers());
        return "updateBooking";
    }
    @PostMapping("/saveUpdate/{id}")
    public String saveBooking(@ModelAttribute("booking") Booking booking,
                              @RequestParam("houseName") Long houseId,
                              @RequestParam("customerName") Long customerId,
                              @PathVariable("id") Long id) throws MyException {
        House house = houseService.getHouseById(houseId);
        Customer customer = customerService.getCustomerById(customerId);
        booking.setHouse(house);
        booking.setCustomer(customer);
        service.updateBookingById(id,booking);
        return "redirect:/bookings";
    }
}
