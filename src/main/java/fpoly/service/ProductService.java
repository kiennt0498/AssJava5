package fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fpoly.entity.Product;

public interface ProductService {

	void deleteAll();

	void deleteAll(Iterable<? extends Product> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Product entity);

	void deleteById(Integer id);

	long count();

	Iterable<Product> findAllById(Iterable<Integer> ids);

	Iterable<Product> findAll();

	boolean existsById(Integer id);

	Optional<Product> findById(Integer id);

	<S extends Product> Iterable<S> saveAll(Iterable<S> entities);

	Page<Product> findAll(Pageable pageable);

	Iterable<Product> findAll(Sort sort);

	<S extends Product> S save(S entity);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	List<Product> findByNameContaining(String name);

}
