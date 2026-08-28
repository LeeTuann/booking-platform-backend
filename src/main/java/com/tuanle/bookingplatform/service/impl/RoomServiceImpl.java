package com.tuanle.bookingplatform.service.impl;

import com.tuanle.bookingplatform.dto.RoomRequestDTO;
import com.tuanle.bookingplatform.dto.RoomResponseDTO;
import com.tuanle.bookingplatform.entity.Room;
import com.tuanle.bookingplatform.repository.RoomRepository;
import com.tuanle.bookingplatform.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    public final RoomRepository roomRepository;
    @Override
    public Page<RoomResponseDTO> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomPage = roomRepository.findAll(pageable);

        // Chuyển đổi từ Page<Room> sang Page<RoomResponseDTO>
        return roomPage.map(this::mapToResponseDTO);
    }

    @Override
    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));
        return mapToResponseDTO(room);
    }

    @Override
    public RoomResponseDTO createRoom(RoomRequestDTO requestDTO) {
        Room room = Room.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .pricePerNight(requestDTO.getPricePerNight())
                .capacity(requestDTO.getCapacity())
                .status("AVAILABLE") // Phong moi se o trang thai trong
                .build();

        Room savedRoom = roomRepository.save(room);
        return mapToResponseDTO(savedRoom);
    }

    @Override
    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO requestDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));
        //Cap nhat thong tin phong
        room.setName(requestDTO.getName());
        room.setDescription(requestDTO.getDescription());
        room.setPricePerNight(requestDTO.getPricePerNight());
        room.setCapacity(requestDTO.getCapacity());

        Room updatedRoom = roomRepository.save(room);
        return mapToResponseDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));

        // Kỹ thuật Soft Delete: Thay vì xóa hẳn khỏi Database, ta đổi trạng thái sang BẢO TRÌ
        room.setStatus("MAINTENANCE");
        roomRepository.save(room);
    }

    // --- Hàm tiện ích hỗ trợ (Mapper) ---
    private RoomResponseDTO mapToResponseDTO(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .pricePerNight(room.getPricePerNight())
                .capacity(room.getCapacity())
                .status(room.getStatus())
                .build();
    }
}
