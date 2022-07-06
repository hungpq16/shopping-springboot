package com.hungpq17.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hungpq17.domain.Product;

public interface ProductService {

	Product getById(Long id);

	void delete(Product entity);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	List<Product> findByNameContaining(String name);

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByNameContaining(String name, Pageable pageable);

}
