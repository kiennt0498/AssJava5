package fpoly.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fpoly.entity.Account;

public interface AccountService {

	void deleteAll();

	void deleteAll(Iterable<? extends Account> entities);

	void deleteAllById(Iterable<? extends String> ids);

	void delete(Account entity);

	void deleteById(String id);

	long count();

	Iterable<Account> findAllById(Iterable<String> ids);

	Iterable<Account> findAll();

	boolean existsById(String id);

	Optional<Account> findById(String id);

	<S extends Account> Iterable<S> saveAll(Iterable<S> entities);

	<S extends Account> S save(S entity);

	Page<Account> findByFullnameContaining(String fullname, Pageable pageable);

	Page<Account> findAll(Pageable pageable);

	Iterable<Account> findAll(Sort sort);

	Account login(String username, String pass);

}
