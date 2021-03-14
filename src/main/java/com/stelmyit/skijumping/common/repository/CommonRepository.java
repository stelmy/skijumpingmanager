package com.stelmyit.skijumping.common.repository;

public interface CommonRepository<T> {
    T get(Long id);
}
