package fpoly.DAO;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fpoly.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	List<Category> findByNameContaining(String name);
	Page<Category> findByNameContaining(String name,Pageable pageable);
	
}
