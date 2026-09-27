package com.ss17hw01lamgiahuyptit015;

import com.ss17hw01lamgiahuyptit015.dto.FoodDto;
import com.ss17hw01lamgiahuyptit015.service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class Ss17Hw01LamGiaHuyPtit015ApplicationTests {

    @Autowired
    private FoodService foodService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisCacheWithJsonSerialization() {
        Long restaurantId = 1L;
        String cacheKey = "foods::" + restaurantId;

        // Xóa cache cũ nếu có
        stringRedisTemplate.delete(cacheKey);

        // Lần 1: Gọi service để lưu cache
        List<FoodDto> foods1 = foodService.getFoodsByRestaurant(restaurantId);
        Assertions.assertNotNull(foods1);
        Assertions.assertFalse(foods1.isEmpty());

        // Kiểm tra xem dữ liệu trong Redis có tồn tại không
        Boolean hasKey = stringRedisTemplate.hasKey(cacheKey);
        Assertions.assertTrue(Boolean.TRUE.equals(hasKey), "Cache key phải tồn tại trong Redis sau lần gọi đầu");

        // Đọc giá trị raw dạng String từ Redis để kiểm tra JSON Serializer (không phải byte binary)
        String rawJsonValue = stringRedisTemplate.opsForValue().get(cacheKey);
        System.out.println(">>> Giá trị JSON trong Redis: " + rawJsonValue);

        Assertions.assertNotNull(rawJsonValue);
        Assertions.assertTrue(rawJsonValue.contains("Phở bò"), "Dữ liệu Redis phải là chuỗi JSON chứa tên món ăn có thể đọc được");
        Assertions.assertTrue(rawJsonValue.startsWith("["), "Dữ liệu JSON của danh sách phải bắt đầu bằng ký tự '['");

        // Lần 2: Lấy dữ liệu từ cache và kiểm tra tính nhất quán
        List<FoodDto> foods2 = foodService.getFoodsByRestaurant(restaurantId);
        Assertions.assertEquals(foods1.size(), foods2.size());
        Assertions.assertEquals(foods1.get(0).getName(), foods2.get(0).getName());
    }

}
