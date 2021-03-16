package com.stelmyit.skijumping.city.service;

import com.stelmyit.skijumping.city.dto.CityDTO;
import com.stelmyit.skijumping.city.factory.CityFactory;
import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.city.repository.CityRepository;
import com.stelmyit.skijumping.common.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class CityService extends CommonService<City, CityDTO, CityRepository, CityFactory> {

    public CityService(CityRepository repository, CityFactory factory) {
        super(repository, factory);
    }
}
