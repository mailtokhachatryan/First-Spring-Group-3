package am.smartcode.first_spring.contoller;

import am.smartcode.first_spring.model.dto.product.CreateProductDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;
import am.smartcode.first_spring.service.product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@Controller
//@ResponseBody
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Integer create(@RequestBody CreateProductDto createProductDto) {
        return productService.create(createProductDto);
    }

    @GetMapping(produces = "application/json")
    public List<ProductDto> getAll() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<ProductDto> all = productService.getAll();
        String s = objectMapper.writeValueAsString(all);

        System.out.println(s);

        return all;
    }


//    @GetMapping("/{id}")
//    public ProductDto getById(@PathVariable Integer id) {
//
//        System.out.println(id);
//        return null;
//    }


}
