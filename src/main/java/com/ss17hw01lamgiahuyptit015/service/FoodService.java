package com.ss17hw01lamgiahuyptit015.service;

import com.ss17hw01lamgiahuyptit015.dto.FoodDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FoodService {
    @Cacheable(value = "foods", key = "#restaurantId")
    public List<FoodDto> getFoodsByRestaurant(Long restaurantId) {
        System.out.println("--> [DB Query] Đang tải danh sách món ăn cho nhà hàng: " + restaurantId);
        return Arrays.asList(
                new FoodDto(1L, "Phở bò", 45000.0),
                new FoodDto(2L, "Bún chả", 50000.0),
                new FoodDto(3L, "Nem rán", 30000.0)
        );
    }
}
