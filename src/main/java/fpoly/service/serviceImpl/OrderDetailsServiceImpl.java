package fpoly.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fpoly.DAO.OrderDetailDao;
import fpoly.entity.Order;
import fpoly.entity.OrderDetail;
import fpoly.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
	@Autowired
	OrderDetailDao dao;
	
	
	
	

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		return dao.findByOrder(order);
	}

	@Override
	public Page<OrderDetail> findByOrder(Order order, Pageable pageable) {
		return dao.findByOrder(order, pageable);
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public Iterable<OrderDetail> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <S extends OrderDetail> Iterable<S> saveAll(Iterable<S> entities) {
		return dao.saveAll(entities);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public Iterable<OrderDetail> findAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<OrderDetail> findAllById(Iterable<Long> ids) {
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
	public void delete(OrderDetail entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		dao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}
	
	
	@Override
	public double getAmount(Order o) {
		 List<OrderDetail> list = findByOrder(o);
		 return list.stream().mapToDouble(item -> item.getProduct().getPrice()*item.getQuantity()).sum();
	}
	
}
