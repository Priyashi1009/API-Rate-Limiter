package com.apirate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class ApirateController {

    @GetMapping("/api/alternative-restaurants")
    public List<String> getNearbyRestaurants(@RequestParam String location) {
        
        return Arrays.asList("Restaurant A", "Restaurant B", "Restaurant C");
    }
}
