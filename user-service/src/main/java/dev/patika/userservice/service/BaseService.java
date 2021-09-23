package dev.patika.userservice.service;

import java.util.Optional;

public interface BaseService<T> {

    Iterable<?> findByAll();

    Optional<?> save(T object);

    Optional<?> update(T object);

    void deleteById(long id);

}
