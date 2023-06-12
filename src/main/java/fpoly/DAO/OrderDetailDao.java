package fpoly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import fpoly.entity.Order;
import fpoly.entity.OrderDetail;

public interface OrderDetailDao extends PagingAndSortingRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrder(Order order);
	Page<OrderDetail> findByOrder(Order order, Pageable pageable);
}
