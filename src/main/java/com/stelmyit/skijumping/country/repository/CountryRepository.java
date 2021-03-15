package com.stelmyit.skijumping.country.repository;

import com.stelmyit.skijumping.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CountryRepository extends JpaRepository<Country, Long> {
}
