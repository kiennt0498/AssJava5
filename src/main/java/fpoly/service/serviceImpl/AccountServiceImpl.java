package fpoly.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fpoly.DAO.AccountDao;
import fpoly.entity.Account;
import fpoly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Override
	public Iterable<Account> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Autowired
	AccountDao dao;

	@Override
	public <S extends Account> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
		return dao.saveAll(entities);
	}

	@Override
	public Optional<Account> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return dao.existsById(id);
	}

	@Override
	public Iterable<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Account> findAllById(Iterable<String> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void delete(Account entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		dao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public Page<Account> findByFullnameContaining(String fullname, Pageable pageable) {
		return dao.findByFullnameContaining(fullname, pageable);
	}
	
	
}
