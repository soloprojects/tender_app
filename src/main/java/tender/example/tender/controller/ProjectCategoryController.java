package tender.example.tender.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tender.example.tender.dto.IndustryRequest;
import tender.example.tender.dto.ProjectCategoryRequest;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.service.ProjectCategoryService;
import tender.example.tender.utility.ResponseHandler;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/project_category")
public class ProjectCategoryController {

    private final ProjectCategoryService service;

    @GetMapping("/list")
    public ResponseEntity<Object> getAll(){
        try{
            return ResponseHandler.generateResponse("Successfully obtained list", HttpStatus.OK, service.findAll());
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, service.findAll());
        }

    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Object> getItem(@PathVariable("id") Long id){
        try{
            return ResponseHandler.generateResponse("Successfully obtained item", HttpStatus.OK, service.findById(id));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ProjectCategoryRequest request){
        try{
            service.create(request);
            return ResponseHandler.generateResponse("Successfully created", HttpStatus.CREATED, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody ProjectCategoryRequest request){
        try{
            return ResponseHandler.generateResponse("Successfully Updated", HttpStatus.CREATED, service.update(request));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, service.findAll());
        }

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id){
        try{
            service.delete(id);
            return ResponseHandler.generateResponse("Successfully obtained item", HttpStatus.OK, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, service.findAll());
        }

    }

    @DeleteMapping("/delete/multiple")
    public ResponseEntity<Object> deleteItem(@RequestParam("ids") List<Long> idList){
        try{
            //for
            service.deleteMultiple(idList);
            return ResponseHandler.generateResponse("Successfully deleted multiple item", HttpStatus.OK, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, service.findAll());
        }

    }

}
