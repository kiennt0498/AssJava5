package fpoly.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fpoly.DAO.OrderDao;
import fpoly.entity.Order;
import fpoly.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao dao;

	@Override
	public <S extends Order> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public Iterable<Order> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
		return dao.saveAll(entities);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public Iterable<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Order> findAllById(Iterable<Long> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void delete(Order entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		dao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public Page<Order> findByFullNameAccount(String name, Pageable pageable) {
		return dao.findByFullNameAccount(name, pageable);
	}

	
	
	
}
