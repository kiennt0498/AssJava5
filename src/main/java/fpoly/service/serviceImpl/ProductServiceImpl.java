package fpoly.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fpoly.DAO.ProductDao;
import fpoly.entity.Product;
import fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao dao;

	@Override
	public <S extends Product> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public Iterable<Product> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		return dao.findByNameContaining(name);
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return dao.findByNameContaining(name, pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
		return dao.saveAll(entities);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public Iterable<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Product> findAllById(Iterable<Integer> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public void delete(Product entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		dao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}
	
	
}
