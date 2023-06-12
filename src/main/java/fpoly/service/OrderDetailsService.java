package fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fpoly.entity.Order;
import fpoly.entity.OrderDetail;

public interface OrderDetailsService {

	

	void deleteAll();

	void deleteAll(Iterable<? extends OrderDetail> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(OrderDetail entity);

	void deleteById(Long id);

	long count();

	Iterable<OrderDetail> findAllById(Iterable<Long> ids);

	Iterable<OrderDetail> findAll();

	boolean existsById(Long id);

	Optional<OrderDetail> findById(Long id);

	<S extends OrderDetail> Iterable<S> saveAll(Iterable<S> entities);

	Page<OrderDetail> findAll(Pageable pageable);

	Iterable<OrderDetail> findAll(Sort sort);

	<S extends OrderDetail> S save(S entity);

	Page<OrderDetail> findByOrder(Order order, Pageable pageable);

	List<OrderDetail> findByOrder(Order order);

	double getAmount(Order o);

	

}
