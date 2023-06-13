package fpoly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import fpoly.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer>{
	List<Product> findByNameContaining(String name);
	Page<Product> findByNameContaining(String name,Pageable pageable);
	@Query(value = "select * from products where id in "
			+ "(select top 4 productid from orderdetails group by productid order by sum(quantity) desc)"
			, nativeQuery = true)
	List<Product> findTop4();
}
