package сoursework.heritage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.service.PersonService;
import сoursework.heritage.model.service.RelationsService;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/{locale:en|ua}/web-api/person/{id}")
public class PersonController {
    private final PersonService personService;
    private final RelationsService relationsService;
    PersonController(PersonService personService, RelationsService relationsService){
        this.personService = personService;
        this.relationsService = relationsService;
    }

    @GetMapping("")
    String getPersonById(Model model,
                         @PathVariable("id") long id,
                         @PathVariable("locale") String thisLocale) {
        Person person = personService.findById(id).orElseThrow(NoSuchElementException::new);
        Person personSpouse = person.getSpouse();
        List<Person> personParents = person.getParents();
        List<Person> personChildren = person.getChildren();
        List<Person> personCousins = person.getCousins();
        model.addAttribute("person", person);
        model.addAttribute("spouse", personSpouse);
        model.addAttribute("personParents", personParents);
        model.addAttribute("personChildren", personChildren);
        model.addAttribute("personCousins", personCousins);
        model.addAttribute("locale",thisLocale);
        return "person";
    }
//    @GetMapping("/delete-person/{idToDelete}")
//    String deletePersonById(Model model,
//                            @PathVariable("id") long id,
//                            @PathVariable("idToDelete") long idToDelete) {
//        personService.deletePersonById(idToDelete);
//        return "redirect:/web-api/person/" + id;
//    }
    @GetMapping("/delete-spouse")
    String deleteSpouseRelation(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("locale") String thisLocale) {
        relationsService.deleteSpouse(id);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/delete-parent/{parentId}")
    String deleteParentRelation(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("parentId") long parentId,
                                @PathVariable("locale") String thisLocale) {
        relationsService.deleteParent(id, parentId);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/delete-child/{childId}")
    String deleteChildRelation(Model model,
                               @PathVariable("id") long id,
                               @PathVariable("childId") long childId,
                               @PathVariable("locale") String thisLocale) {
        relationsService.deleteParent(childId, id);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/delete-cousin/{cousinId}")
    String deleteCousinRelation(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("cousinId") long cousinId,
                                @PathVariable("locale") String thisLocale) {
        relationsService.deleteCousin(id, cousinId);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/add-spouse")
    String showSpousesAvailable(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        model.addAttribute("personId", id);
        model.addAttribute("spouses", list);
        model.addAttribute("locale",thisLocale);
        return "addSpouse";
    }
    @GetMapping("/add-spouse/{spouseId}")
    String addSpouseToPerson(Model model,
                             @PathVariable("id") long id,
                             @PathVariable("spouseId") long spouseId,
                             @PathVariable("locale") String thisLocale) {
        relationsService.addSpouse(id, spouseId);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/add-child")
    String showChildrenAvailable(Model model,
                                 @PathVariable("id") long id,
                                 @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        model.addAttribute("personId", id);
        model.addAttribute("children", list);
        model.addAttribute("locale",thisLocale);
        return "addChild";
    }
    @GetMapping("/add-child/{childId}")
    String addChildrenToPerson(Model model,
                               @PathVariable("id") long id,
                               @PathVariable("childId") long childId,
                               @PathVariable("locale") String thisLocale) {
        relationsService.addParent(childId, id);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/add-parent")
    String showParentsAvailable(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        model.addAttribute("personId", id);
        model.addAttribute("parents", list);
        model.addAttribute("locale",thisLocale);
        return "addParent";
    }
    @GetMapping("/add-parent/{parentId}")
    String addParentToPerson(Model model,
                             @PathVariable("id") long id,
                             @PathVariable("parentId") long parentId,
                             @PathVariable("locale") String thisLocale) {
        relationsService.addParent(id, parentId);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/add-cousin")
    String showCousinsAvailable(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        model.addAttribute("personId", id);
        model.addAttribute("cousins", list);
        model.addAttribute("locale",thisLocale);
        return "addCousin";
    }
    @GetMapping("/add-cousin/{cousinId}")
    String addCousinToPerson(Model model,
                             @PathVariable("id") long id,
                             @PathVariable("cousinId") long cousinId,
                             @PathVariable("locale") String thisLocale) {
        relationsService.addCousin(id, cousinId);
        return "redirect:/"+ thisLocale + "/web-api/person/" + id;
    }
    @GetMapping("/get-ancestors")
    String getPersonAncestors(Model model,
                              @PathVariable("id") long id,
                              @PathVariable("locale") String thisLocale) {
        List<Person> personAncestors = relationsService.getAncestors(id);
        personService.sortPersonListById(personAncestors);
        if(personAncestors.isEmpty()) System.out.println("NO ANCESTORS");
        model.addAttribute("personId", id);
        model.addAttribute("ancestors", personAncestors);
        model.addAttribute("locale",thisLocale);
        return "personAncestors";
    }
    @GetMapping("/get-descendants")
    String getPersonDescendants(Model model,
                                @PathVariable("id") long id,
                                @PathVariable("locale") String thisLocale) {
        List<Person> personDescendants = relationsService.getDescendants(id);
        personService.sortPersonListById(personDescendants);
        if(personDescendants.isEmpty()) System.out.println("NO DESCENDANTS");
        model.addAttribute("personId", id);
        model.addAttribute("descendants", personDescendants);
        model.addAttribute("locale",thisLocale);
        return "personDescendants";
    }
    @GetMapping("/get-relatives")
    String getPersonRelatives(Model model,
                              @PathVariable("id") long id,
                              @PathVariable("locale") String thisLocale) {
        List<Person> personRelatives = relationsService.getRelatives(id);
//        personService.sortPersonListById(personRelatives);
        if(personRelatives.isEmpty()) System.out.println("NO RELATIVES");
        model.addAttribute("personId", id);
        model.addAttribute("relatives", personRelatives);
        model.addAttribute("locale",thisLocale);
        return "personRelatives";
    }
    @GetMapping("/get-relation")
    String getPersonRelation(Model model,
                             @PathVariable("id") long id,
                             @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        model.addAttribute("personId", id);
        model.addAttribute("persons", list);
        model.addAttribute("relation", "");
        model.addAttribute("locale",thisLocale);
        return "findRelation";
    }
    @GetMapping("/get-relation/{targetId}")
    String getPersonRelationById(Model model,
                                 @PathVariable("id") long id,
                                 @PathVariable("targetId") long targetId,
                                 @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        personService.sortPersonListById(list);
        String relation = relationsService.getRelation(id, targetId);
        model.addAttribute("personId", id);
        model.addAttribute("persons", list);
        model.addAttribute("relation",relation);
        model.addAttribute("locale",thisLocale);
        return "findRelation";
    }



}
