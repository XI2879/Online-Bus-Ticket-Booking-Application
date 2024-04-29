package com.example.BusTicketBookingBackend.repository;



import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{


	public Route findByRouteFromAndRouteTo(String routeFrom,String routeTo);

	@Query("SELECT r.busList FROM Route r WHERE r.routeFrom = :routeFrom AND r.routeTo = :routeTo")
	List<Bus> findBusesByRouteFromAndRouteTo(@Param("routeFrom") String routeFrom, @Param("routeTo") String routeTo);

}
