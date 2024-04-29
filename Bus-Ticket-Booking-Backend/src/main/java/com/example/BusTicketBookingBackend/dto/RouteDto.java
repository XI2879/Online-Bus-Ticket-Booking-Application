package com.example.BusTicketBookingBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private String routeFrom;
    private String routeTo;
    private Integer distance;
}
