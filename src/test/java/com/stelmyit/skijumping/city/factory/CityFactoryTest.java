package com.stelmyit.skijumping.city.factory;

import com.stelmyit.skijumping.city.dto.CityDTO;
import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.country.model.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityFactoryTest {

    private final CityFactory uut = new CityFactory();

    private static final String CITY_NAME = "Zakopane";
    private static final String COUNTRY_CODE = "POL";

    @Test
    public void shouldCreateDto() {
        // given
        final City city = City.builder()
            .name(CITY_NAME)
            .country(Country.builder().code(COUNTRY_CODE).build())
            .build();

        // when
        final CityDTO dto = uut.createDTO(city);

        // then
        assertThat(dto.getName()).isEqualTo(CITY_NAME);
        assertThat(dto.getCountryCode()).isEqualTo(COUNTRY_CODE);
    }
}
