package com.tuanle.bookingplatform.service;

import com.tuanle.bookingplatform.dto.RoomRequestDTO;
import com.tuanle.bookingplatform.dto.RoomResponseDTO;
import org.springframework.data.domain.Page;

public interface RoomService {
    Page<RoomResponseDTO> getAllRooms(int page, int size);
    RoomResponseDTO getRoomById(Long id);
    RoomResponseDTO createRoom(RoomRequestDTO roomRequestDTO);
    RoomResponseDTO updateRoom(Long id, RoomRequestDTO roomRequestDTO);
    void deleteRoom(Long id);
}
