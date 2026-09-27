package com.ss17hw01lamgiahuyptit015.controller;

import com.ss17hw01lamgiahuyptit015.dto.FoodDto;
import com.ss17hw01lamgiahuyptit015.service.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{restaurantId}")
    public List<FoodDto> getFoodsByRestaurant(@PathVariable Long restaurantId) {
        return foodService.getFoodsByRestaurant(restaurantId);
    }
}
