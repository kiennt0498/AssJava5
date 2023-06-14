package fpoly.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fpoly.entity.Order;

public interface OrderService {

	void deleteAll();

	void deleteAll(Iterable<? extends Order> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Order entity);

	void deleteById(Long id);

	long count();

	Iterable<Order> findAllById(Iterable<Long> ids);

	Iterable<Order> findAll();

	boolean existsById(Long id);

	Optional<Order> findById(Long id);

	<S extends Order> Iterable<S> saveAll(Iterable<S> entities);

	Page<Order> findAll(Pageable pageable);

	Iterable<Order> findAll(Sort sort);

	<S extends Order> S save(S entity);

	Page<Order> findByFullNameAccount(String name, Pageable pageable);

	Order findNewOrder();

	Page<Order> findByUserNameAccount(String name, Pageable pageable);

	

}
