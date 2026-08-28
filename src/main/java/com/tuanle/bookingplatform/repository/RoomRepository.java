package com.tuanle.bookingplatform.repository;

import com.tuanle.bookingplatform.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    Page<Room> findByStatus(String status ,Pageable pageable);
    Page<Room> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
