package com.example.demo.ShopingCart;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    @Query("SELECT SUM(c.priceSummaryforTheSameSubject) FROM CartEntity c WHERE c.id_user= :userId")
    BigDecimal sumAllPriceSummaryForUser(@Param("userId") Long userId);

//    @Query("SELECT c.subject FROM CartEntity c WHERE c.id= :userId")
//    CartEntity findBySubjectId(Long id);
    Optional<CartEntity> findBySubjectId(Long id);

    void deleteBySubjectId(Long id);
//    Optional<ShoppingCart> findBySubjectId(Long subjectId);
}
