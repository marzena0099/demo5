package com.example.demo.Subjects;

import com.example.demo.ShopingCart.CartRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final CartRepository cartRepository;


    @Transactional
    public SubjectEntity add(String name, Long quantity, BigDecimal price) {
        var entity = new SubjectEntity();
        entity.setName(name);
        entity.setQuantity(quantity);
        entity.setPrice(price);
        return subjectRepository.save(entity);
    }

    @Transactional
    public Optional<SubjectEntity> remove(Long id) {
        return subjectRepository
                .findById(id) // Optional<SubjectEntity>
                .map(subjectEntity -> {
                    cartRepository.deleteBySubjectId(id);
                    subjectRepository.delete(subjectEntity);
                    return subjectEntity;
                });
    }
@Transactional
public Optional<SubjectEntity> edit(Long id, Long quantity){
      return subjectRepository.findById(id).map(subjectEntity -> {
            subjectEntity.setQuantity(quantity);
            subjectRepository.save(subjectEntity);
           return subjectEntity;
       });

}
    public List<SubjectEntity> getAll() {
        return subjectRepository.findAll();
    }
}
