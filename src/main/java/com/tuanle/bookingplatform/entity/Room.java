package com.tuanle.bookingplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Room {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false)
   private String name;
   @Column(columnDefinition = "TEXT")
   private String description;
   @Column(nullable = false)
   private BigDecimal pricePerNight;
   @Column(nullable = false)
   private Integer capacity;
   @Column(nullable = false)
   private String status;
}
