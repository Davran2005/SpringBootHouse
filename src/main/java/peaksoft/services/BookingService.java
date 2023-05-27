package peaksoft.services;

import peaksoft.exception.MyException;
import peaksoft.model.Booking;

import java.util.List;

public interface BookingService {
    void saveBooking(Booking booking);

    Booking getBookingById(Long id) throws MyException;

    List<Booking> getAllBooking();

    void updateBookingById(Long id, Booking booking) throws MyException;

    void deleteBookingById(Long id) throws MyException;

}
