package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; // ✅ Import List
import java.util.Optional;

import com.example.repo.DemoRepository;
import com.example.Entity.DemoEntity;
import com.example.exception.ResourceNotFoundException;

// ✅ Import your entity
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoRepository demoRepository;

    @GetMapping
    public List<DemoEntity> getUser() {
        return demoRepository.findAll(); // ✅ Return the list of users from the DB
    }

    @PostMapping
    public DemoEntity createUser(@RequestBody DemoEntity user) {
        return demoRepository.save(user);

    }

    @GetMapping("/{id}")
public DemoEntity getUserById(@PathVariable Long id) {
    return demoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
}


@PutMapping("/{id}")
public DemoEntity updateUser( @PathVariable Long id,@RequestBody DemoEntity user){
DemoEntity userData = demoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            userData.setEmail(user.getEmail());
            userData.setName(user.getName());
             return demoRepository.save(userData);
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
       DemoEntity userData = demoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        demoRepository.delete(userData);
        return ResponseEntity.ok().build();
    }




}
