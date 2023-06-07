package fpoly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import fpoly.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer>{
	List<Product> findByNameContaining(String name);
	Page<Product> findByNameContaining(String name,Pageable pageable);
}
