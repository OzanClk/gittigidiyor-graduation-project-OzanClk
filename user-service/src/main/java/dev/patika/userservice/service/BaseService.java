package dev.patika.userservice.service;

import java.util.Map;
import java.util.Optional;

public interface BaseService<T> {

    Iterable<?> findByAll();

    Optional<?> save(T object);

    Optional<?> update(T object,Long id);

    Optional<?> findById(Long id);

    Map<?,?> deleteById(long id);

}
