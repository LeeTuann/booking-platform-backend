package com.tuanle.bookingplatform.repository;

import com.tuanle.bookingplatform.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    Page<Room> findByStatus(String status ,Pageable pageable);
    Page<Room> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    Optional<Room> findByIdForUpdate(Long id);
}
