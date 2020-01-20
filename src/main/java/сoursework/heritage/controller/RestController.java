package сoursework.heritage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import сoursework.heritage.model.dto.PersonDTO;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.service.PersonService;

import java.net.URI;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest-api/people")
public class RestController {
    private final PersonService personService;

    RestController(PersonService personService){
        this.personService = personService;
    }
    @GetMapping("")
    Iterable<Person> getAllPersons() {
        Iterable<Person> list = personService.findAll();
//        list.forEach(System.out::println);
        return list;
    }
    @GetMapping("{id}")
    Person getPersonById(@PathVariable("id") long id) {
        return personService.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @PostMapping
    ResponseEntity<Object> addPerson(@RequestBody PersonDTO person) {
        Person newPerson = personService.addPerson(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newPerson.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("{id}")
    void deletePerson(@PathVariable long id) { personService.deletePersonById(id); }

    @PutMapping("{id}")
    ResponseEntity<Object> updatePerson(@PathVariable long id,
                                        @RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "sex", required = false) Person.Sex sex,
                                        @RequestParam(name = "birthDate", required = false) Date birthDate,
                                        @RequestParam(name = "deathDate", required = false) Date deathDate,
                                        @RequestParam(name = "biography", required = false) String biography) {
        Optional<Person> personForUpdate = personService.findById(id);
        if (!personForUpdate.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personService.updatePerson(id, name, sex , birthDate , deathDate, biography);
        return ResponseEntity.noContent().build();
    }
}
