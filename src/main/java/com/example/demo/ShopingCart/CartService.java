//package com.example.demo.ShopingCart;
//
//import com.example.demo.Subjects.SubjectEntity;
//import com.example.demo.Subjects.SubjectRepository;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import com.example.demo.Subjects.SubjectRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//@AllArgsConstructor

package com.example.demo.ShopingCart;

import com.example.demo.Subjects.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class CartService {
    private final SubjectRepository subjectRepository;
    private final CartRepository cartRepository;
    @Getter
    @Setter
    private BigDecimal totalPrice = BigDecimal.ZERO;  // Inicjalizacja pola totalPrice
    private BigDecimal valueCart = BigDecimal.ZERO;

    public CartService(SubjectRepository subjectRepository, CartRepository cartRepository) {
        this.subjectRepository = subjectRepository;
//        this.totalPrice = BigDecimal.ZERO;  // Inicjalizacja totalPrice
        this.cartRepository = cartRepository;
    }

    @Transactional
    public boolean checkAvalibility(Long id, Long quantity) {
        boolean isAvailable = subjectRepository.findById(id)
                .map(subject -> quantity <= subject.getQuantity())
                .orElse(false);

        if (isAvailable) {
            Optional<CartEntity> cartEntityOpt = cartRepository.findBySubjectId(id);
            if (cartEntityOpt.isPresent()) {
                CartEntity cartEntity = cartEntityOpt.get();
                cartEntity.setQuantity(quantity);
                cartEntity.setPriceSummaryforTheSameSubject(cartEntity.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
                cartRepository.save(cartEntity);
            } else {
                var entity = new CartEntity();
                entity.setSubject(subjectRepository.findById(id).orElseThrow());
                entity.setPriceSummaryforTheSameSubject(subjectRepository.findById(id).orElseThrow().getPrice().multiply(BigDecimal.valueOf(quantity)));
                entity.setQuantity(quantity);
                entity.setUnitPrice(subjectRepository.findById(id).orElseThrow().getPrice());
                cartRepository.save(entity);
            }

//            });
        }
        else{
            throw new RuntimeException("no exist quality");}

        return isAvailable;
    }

    public Optional<Object> remove(Long id) {
        return cartRepository.findById(id)
                .map(subject -> {
                    cartRepository.delete(subject);
                    return subject;
                });

    }

    public BigDecimal reloadSummaryPrice(long id) {
        return cartRepository.sumAllPriceSummaryForUser(id);

    }
}

