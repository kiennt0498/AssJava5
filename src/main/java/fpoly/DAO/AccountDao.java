package fpoly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import fpoly.entity.Account;

public interface AccountDao extends PagingAndSortingRepository<Account, String>{
	Page<Account> findByFullnameContaining(String fullname,Pageable pageable);
}
