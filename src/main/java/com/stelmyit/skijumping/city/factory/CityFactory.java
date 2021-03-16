package com.stelmyit.skijumping.city.factory;

import com.stelmyit.skijumping.city.dto.CityDTO;
import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.common.factory.CommonFactory;
import org.springframework.stereotype.Service;

@Service
public class CityFactory extends CommonFactory<City, CityDTO> {

    @Override
    public City createEntity(CityDTO cityDTO) {
        return null;
    }

    @Override
    public CityDTO createDTO(City city) {
        return CityDTO.builder()
            .name(city.getName())
            .countryCode(city.getCountry().getCode())
            .build();
    }

}
