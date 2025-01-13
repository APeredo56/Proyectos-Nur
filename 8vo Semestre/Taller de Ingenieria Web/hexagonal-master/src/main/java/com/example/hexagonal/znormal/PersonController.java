package com.example.hexagonal.znormal;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;



    @GetMapping("/{id}")
    public Person personById(@PathVariable long id){
        return personRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public  Person savePerson(@RequestBody Person person){
        return personRepository.save(person);
    }
}
