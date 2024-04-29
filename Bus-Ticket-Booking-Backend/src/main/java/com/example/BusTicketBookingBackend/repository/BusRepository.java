package com.example.BusTicketBookingBackend.repository;


import com.example.BusTicketBookingBackend.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
   public List<Bus> findByBusType(String busType);
   @Query("SELECT b FROM Bus b WHERE b.busId = :busId AND b.busJourneyDate = :journeyDate")
   Optional<Bus> findByIdAndBusJourneyDate(@Param("busId") Integer busId, @Param("journeyDate") LocalDate journeyDate);

//   public Bus findByRouteFromAndRouteTo(String routeFrom, String routeTo);
}
