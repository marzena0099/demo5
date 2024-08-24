package com.example.demo.Subjects;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public SubjectEntity createSubject(@RequestBody SubjectEntity subject) {
        return subjectService.add(subject.getName(),subject.getQuantity(),subject.getPrice());
    }

@PutMapping
public ResponseEntity<Void> editQuantity(@RequestParam Long id, @RequestParam Long quantity){
         subjectService.edit(id,quantity);
         return ResponseEntity.ok().build();
}


    @DeleteMapping("/{id}")
    ResponseEntity<SubjectEntity> delete(@PathVariable Long id) {
        return subjectService
                .remove(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<SubjectEntity> getAll() {
        return subjectService.getAll();
    }

}

