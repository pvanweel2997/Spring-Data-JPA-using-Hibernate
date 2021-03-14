package com.bharath.springdata.product.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.bharath.springdata.product.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	
	List<Product> findByName(String name);
	
	List<Product> findByNameAndDesc(String name, String desc);
	
	List<Product> findByPriceGreaterThan(Double price);
	
	List<Product> findByDescContains(String desc);
	
	List<Product> findByPriceBetween(Double priceStart, Double priceEnd);
	
	List<Product> findByDescLike(String desc);
	
	List<Product> findByIdIn(List<Integer> ids, Pageable pageable);
	
	@Query(value="CALL GetAllProducts",nativeQuery=true	)
	List<Product> findAllProductsSP();
	
	@Query(value="CALL GetAllProductsByPrice (:price_in)",nativeQuery=true	)
	List<Product> findAllProductsByPriceSP(double price_in);
	
	@Query(value="CALL GetAllProductsCountByPrice (:price_in)",nativeQuery=true	)
	int findProductCountByPriceSP(double price_in);

}
