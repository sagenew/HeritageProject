package сoursework.heritage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import сoursework.heritage.model.dto.PersonDTO;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.service.PersonService;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/{locale:en|ua}/web-api/person-list")
public class MainController {
    private final PersonService personService;

    MainController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("")
    String getAllPersons(Model model,
                         @PathVariable("locale") String thisLocale) {
        List<Person> list = personService.findAll();
        model.addAttribute("persons", list);
        return "personList";
    }

    @GetMapping("/delete-person/{id}")
    String deletePersonById(Model model,
                            @PathVariable("id") long id,
                            @PathVariable("locale") String thisLocale) {
        personService.deletePersonById(id);
        return "redirect:/" + thisLocale + "/web-api/person-list";
    }

    @GetMapping("/add-person")
    String updatePerson(Model model,
                        @PathVariable("locale") String thisLocale) {
        PersonDTO personDTO = new PersonDTO();
        model.addAttribute("personForm", personDTO);
        model.addAttribute("locale", thisLocale);
        return "addPerson";
    }

    @PostMapping("/add-person")
    String submitPerson(@Valid @ModelAttribute("personForm") PersonDTO personDTO,
                        BindingResult result, ModelMap model,
                        @PathVariable("locale") String thisLocale) {
//        if (result.hasErrors()) {
//            return "error";
//        }
        System.out.println("POST METHOD INITIATED");
        System.out.println(personDTO.getName());
        System.out.println(personDTO.getBirthDate());
        personService.addPerson(personDTO);
        model.addAttribute("locale", thisLocale);
        return "redirect:/" + thisLocale + "/web-api/person-list";
    }

    @GetMapping("/update-person/{id}")
    String updatePerson(Model model, @PathVariable("id") long id, @PathVariable("locale") String thisLocale) {
        Person person = personService.findById(id).orElseThrow(NoSuchElementException::new);
        PersonDTO personDTO = new PersonDTO();
        System.out.println(person.getBirthDate());
        System.out.println(person.getDeathDate());
        String birthDateString = "";
        String deathDateString = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(person.getBirthDate() != null) birthDateString = dateFormat.format(person.getBirthDate());
        if(person.getDeathDate() != null) deathDateString = dateFormat.format(person.getDeathDate());
        System.out.println(birthDateString);
        System.out.println(deathDateString);
        model.addAttribute("personForm", personDTO);
        model.addAttribute("personId", person.getId());
        model.addAttribute("personName", person.getName());
        model.addAttribute("personSex", person.getSex());
        model.addAttribute("personBirthDate", birthDateString);
        model.addAttribute("personDeathDate", deathDateString);
        model.addAttribute("personBiography", person.getBiography());
        model.addAttribute("locale",thisLocale);
        return "updatePerson";
    }
    @PostMapping("/update-person/{id}")
    String submitUpdatedPerson(@Valid @ModelAttribute("personForm") PersonDTO personDTO,
                               BindingResult result,
                               ModelMap model,
                               @PathVariable("id") long id,
                               @PathVariable("locale") String thisLocale) {
        System.out.println("UPDATE POST METHOD INITIATED");
        System.out.println(personDTO.getName());
        System.out.println(personDTO.getBirthDate());
        personService.updatePerson(id, personDTO.getName(), personDTO.getSex(), personDTO.getBirthDate(), personDTO.getDeathDate(),personDTO.getBiography());
        model.addAttribute("locale",thisLocale);
        return "redirect:/" + thisLocale + "/web-api/person-list";
    }


}
