package сoursework.heritage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.service.PersonService;
import сoursework.heritage.model.service.RelationsService;

@RestController
@RequestMapping("/rest-api/relations")
public class RestRelationsController {
    private final RelationsService relationsService;

    RestRelationsController(RelationsService relationsService) {
        this.relationsService = relationsService;
    }
    @GetMapping("/getparents/{id}")
    Iterable<Person> getParentsByPersonId(@PathVariable("id") long id) {
        return relationsService.getParents(id);
    }
    @GetMapping("/getchildren/{id}")
    Iterable<Person> getChildrenByPersonId(@PathVariable("id") long id) {
        return relationsService.getChildren(id);
    }
    @GetMapping("/getspouse/{id}")
    Person getSpouseByPersonId(@PathVariable("id") long id) {
        return relationsService.getSpouse(id);
    }
    @GetMapping("/getcousins/{id}")
    Iterable<Person> getCousinsByPersonId(@PathVariable("id") long id) {
        return relationsService.getCousins(id);
    }
}