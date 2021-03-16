package com.stelmyit.skijumping.city.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO {

    private String name;
    private String countryCode;

}
