package com.ss17hw01lamgiahuyptit015;

import com.ss17hw01lamgiahuyptit015.service.FoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ss17Hw01LamGiaHuyPtit015Application {

    public static void main(String[] args) {
        SpringApplication.run(Ss17Hw01LamGiaHuyPtit015Application.class, args);
    }

    @Bean
    public CommandLineRunner demoRunner(FoodService foodService) {
        return args -> {
            System.out.println("\n======================================================");
            System.out.println(">>> DEMO KIỂM TRA REDIS DISTRIBUTED CACHE");
            System.out.println("======================================================");
            System.out.println("Lần gọi 1 (Chưa có cache -> Sẽ log [DB Query]):");
            foodService.getFoodsByRestaurant(1L);

            System.out.println("\nLần gọi 2 (Đã có cache trong Redis -> KHÔNG log [DB Query]):");
            foodService.getFoodsByRestaurant(1L);
            System.out.println("======================================================\n");
        };
    }
}
