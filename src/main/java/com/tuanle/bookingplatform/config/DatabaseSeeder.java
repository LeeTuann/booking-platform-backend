package com.tuanle.bookingplatform.config;

import com.tuanle.bookingplatform.entity.Room;
import com.tuanle.bookingplatform.entity.User;
import com.tuanle.bookingplatform.repository.RoomRepository;
import com.tuanle.bookingplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner{
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedRooms();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@tuanle.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();
            User user = User.builder()
                    .username("tuanle")
                    .email("user@tuanle.com")
                    .password(passwordEncoder.encode("user123"))
                    .role("USER")
                    .build();
            userRepository.saveAll(List.of(admin, user));
        }
    }
    private void seedRooms() {
        if (roomRepository.count() == 0) {
            Room room1 = Room.builder()
                    .name("Phòng VIP Hướng Biển")
                    .description("Phòng cao cấp view biển, giường đôi chuẩn 5 sao.")
                    .pricePerNight(new BigDecimal("2500000"))
                    .capacity(2)
                    .status("AVAILABLE")
                    .build();
            Room room2 = Room.builder()
                    .name("Phòng Standard")
                    .description("Phòng tiêu chuẩn tiện nghi, không gian ấm cúng.")
                    .pricePerNight(new BigDecimal("800000"))
                    .capacity(2)
                    .status("AVAILABLE")
                    .build();
            Room room3 = Room.builder()
                    .name("Phòng Family")
                    .description("Phòng gia đình siêu rộng, phù hợp 4 người.")
                    .pricePerNight(new BigDecimal("1500000"))
                    .capacity(4)
                    .status("MAINTENANCE")
                    .build();
            Room room4 = Room.builder()
                    .name("Phòng Deluxe City View")
                    .description("Phòng sang trọng với ban công nhìn toàn cảnh thành phố.")
                    .pricePerNight(new BigDecimal("1200000"))
                    .capacity(2)
                    .status("AVAILABLE")
                    .build();
            Room room5 = Room.builder()
                    .name("Phòng Superior Twin")
                    .description("Phòng 2 giường đơn, phù hợp cho bạn bè đi du lịch.")
                    .pricePerNight(new BigDecimal("950000"))
                    .capacity(2)
                    .status("AVAILABLE")
                    .build();
            Room room6 = Room.builder()
                    .name("Presidential Suite")
                    .description("Phòng tổng thống siêu sang trọng, dịch vụ đặc quyền.")
                    .pricePerNight(new BigDecimal("5000000"))
                    .capacity(4)
                    .status("AVAILABLE")
                    .build();
            Room room7 = Room.builder()
                    .name("Phòng Single Basic")
                    .description("Phòng 1 giường đơn gọn gàng, tiết kiệm cho người đi công tác.")
                    .pricePerNight(new BigDecimal("500000"))
                    .capacity(1)
                    .status("AVAILABLE")
                    .build();
            Room room8 = Room.builder()
                    .name("Couple Getaway")
                    .description("Phòng lãng mạn dành cho cặp đôi, tặng kèm rượu vang.")
                    .pricePerNight(new BigDecimal("1800000"))
                    .capacity(2)
                    .status("BOOKED")
                    .build();
            Room room9 = Room.builder()
                    .name("Connecting Family Room")
                    .description("2 phòng thông nhau, cực kỳ thoải mái cho gia đình lớn.")
                    .pricePerNight(new BigDecimal("2200000"))
                    .capacity(6)
                    .status("AVAILABLE")
                    .build();
            Room room10 = Room.builder()
                    .name("Budget Dormitory")
                    .description("Giường tầng tập thể, phù hợp cho dân phượt (giá tính theo giường).")
                    .pricePerNight(new BigDecimal("200000"))
                    .capacity(8)
                    .status("MAINTENANCE")
                    .build();

            roomRepository.saveAll(List.of(
                    room1, room2, room3, room4, room5,
                    room6, room7, room8, room9, room10
            ));
            System.out.println("Database seeded with sample data.");
        }
    }
}
