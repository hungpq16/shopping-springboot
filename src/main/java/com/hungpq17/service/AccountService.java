package com.hungpq17.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hungpq17.domain.Account;

public interface AccountService {

	<S extends Account> List<S> findAll(Example<S> example, Sort sort);

	Account getById(String id);

	void deleteAll(Iterable<? extends Account> entities);

	void delete(Account entity);

	<S extends Account> boolean exists(Example<S> example);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(String id);

	Optional<Account> findById(String id);

	List<Account> findAll();

	<S extends Account> Optional<S> findOne(Example<S> example);

	<S extends Account> S save(S entity);

	void deleteById(String id);

	Account login(String username, String password);

}
