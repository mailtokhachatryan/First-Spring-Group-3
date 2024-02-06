package am.smartcode.first_spring.contoller;

import am.smartcode.first_spring.exception.EntityNotFoundException;
import am.smartcode.first_spring.model.dto.category.CategoryDto;
import am.smartcode.first_spring.model.dto.category.CreateCategoryDto;
import am.smartcode.first_spring.model.dto.category.UpdateCategoryDto;
import am.smartcode.first_spring.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> creat(@RequestBody CreateCategoryDto createCategoryDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(createCategoryDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoryService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@RequestBody UpdateCategoryDto updateCategoryDto, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoryService.update(updateCategoryDto, id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }


}
