package peaksoft.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.repository.BookingRepository;
import peaksoft.repository.CustomerRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.services.BookingService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final HouseRepository houseRepository;
    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);

    }

    @Override
    public Booking getBookingById(Long id) throws MyException {
        return bookingRepository.findById(id).orElseThrow(()->new MyException("Booking by id: " + id + " is not found!!!"));
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void updateBookingById(Long id, Booking booking) throws MyException {

        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new MyException("Booking by id: " + id + " is not found!!!"));
        booking1.setCustomer(booking.getCustomer());
        booking1.setHouse(booking.getHouse());
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBookingById(Long id) throws MyException {
        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new MyException("Booking by id: " + id + " is not found!!!"));
        bookingRepository.delete(booking1);
    }

}
