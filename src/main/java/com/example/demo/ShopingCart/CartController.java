package com.example.demo.ShopingCart;

import com.example.demo.Subjects.SubjectEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/cart")
public class CartController {
    public final CartService cartService;

    @GetMapping("/getAllCost/{id}")
    public BigDecimal adjust(@PathVariable long id){
        return cartService.reloadSummaryPrice(id);
    }

@PostMapping()
    public boolean buttonEditQuantity(@RequestBody SubjectEntity subject){
    //przekazacIdUzytkownika(z tokena?z RequestParam?)
    return cartService.checkAvalibility(subject.getId(),subject.getQuantity());
}
@DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
    return cartService.remove(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


}
