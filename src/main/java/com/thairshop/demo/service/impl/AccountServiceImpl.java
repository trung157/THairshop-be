package com.thairshop.demo.service.impl;

import java.util.Optional;

public interface AccountServiceImpl<T> {
	Iterable<T> findAll();

    Optional<T> findById(int id);

    T save(T t);

    void remove(int id);
}
