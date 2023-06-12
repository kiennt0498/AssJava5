package fpoly.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import fpoly.entity.Order;

public interface OrderDao extends PagingAndSortingRepository<Order, Long>{
	@Query("select o from Order o where o.account.fullname=?1")
	Page<Order> findByFullNameAccount(String name,Pageable pageable);
}
