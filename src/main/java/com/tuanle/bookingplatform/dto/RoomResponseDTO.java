package com.tuanle.bookingplatform.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RoomResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal pricePerNight;
    private Integer capacity;
    private String status;
}
