package com.tuanle.bookingplatform.controller;

import com.tuanle.bookingplatform.dto.RoomRequestDTO;
import com.tuanle.bookingplatform.dto.RoomResponseDTO;
import com.tuanle.bookingplatform.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "Room Management", description = "Các API quản lý phòng khách sạn")
public class RoomController {
    private final RoomService roomService;
    @Operation(summary = "Lấy danh sách phòng", description = "Trả về danh sách phòng có phân trang")
    @GetMapping
    public ResponseEntity<Page<RoomResponseDTO>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roomService.getAllRooms(page, size));
    }
    @Operation(summary = "Lấy chi tiết một phòng", description = "Truyền vào ID của phòng để lấy thông tin chi tiết")
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    @Operation(summary = "Tạo phòng mới", description = "Thêm một phòng mới vào hệ thống")
    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody RoomRequestDTO requestDTO) {
        RoomResponseDTO createdRoom = roomService.createRoom(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }
    @Operation(summary = "Cập nhật phòng", description = "Chỉnh sửa thông tin phòng đã có")
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable Long id,
            @RequestBody RoomRequestDTO requestDTO) {
        return ResponseEntity.ok(roomService.updateRoom(id, requestDTO));
    }
    @Operation(summary = "Xóa phòng (Soft Delete)", description = "Chuyển trạng thái phòng sang BẢO TRÌ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
