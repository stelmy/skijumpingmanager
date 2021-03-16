package com.stelmyit.skijumping.city.controller;

import com.stelmyit.skijumping.city.dto.CityDTO;
import com.stelmyit.skijumping.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(method = GET)
    public List<CityDTO> getAllCities() {
        return cityService.getAll();
    }

}
