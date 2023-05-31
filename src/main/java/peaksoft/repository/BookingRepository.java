package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.model.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
