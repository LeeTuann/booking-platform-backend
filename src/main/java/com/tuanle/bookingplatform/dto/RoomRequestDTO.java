package com.tuanle.bookingplatform.dto;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class RoomRequestDTO {
    private String name;
    private String description;
    private BigDecimal pricePerNight;
    private Integer capacity;
}
