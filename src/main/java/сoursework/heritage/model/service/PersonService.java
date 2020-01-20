package сoursework.heritage.model.service;

import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;
import сoursework.heritage.model.dto.PersonDTO;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.repository.PersonRepo;

import javax.annotation.PostConstruct;
import java.rmi.NotBoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonService {
    private final PersonRepo personRepo;
    PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> sortPersonListById(List <Person> list) {
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getId().compareTo(person2.getId());
            }
        });
        return list;
    }

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Optional<Person> findById(long id) {
        return personRepo.findById(id);
    }

    public Person addPerson (PersonDTO person) {
        if(person.getBirthDate() != null
                && person.getDeathDate() != null
                && person.getBirthDate().after( person.getDeathDate())) {
            throw new IllegalArgumentException("Whomst`d`ve died can not be born.");
        }
        Person newPerson = new Person.Builder()
                .setName(person.getName())
                .setSex(person.getSex())
                .setBirthDate(person.getBirthDate())
                .setDeathDate(person.getDeathDate())
                .setBiography(person.getBiography())
                .build();
        return personRepo.save(newPerson);
    }

    public void deletePersonById(long id) {
        Optional<Person> person = findById(id);

        if (!person.isPresent()) {
            throw new NoSuchElementException();
        }
        Person personToDelete = person.get();

        Person personSpouse = personToDelete.getSpouse();
        if(personSpouse != null ) {
            personSpouse.setSpouse(null);
            personToDelete.setSpouse(null);
        }

        List<Person> personChildren = personToDelete.getChildren();
        if(!personChildren.isEmpty()) {
            for (Person i : personChildren) {
                i.getParents().remove(personToDelete);
            }
            personToDelete.setChildren(null);
        }

        List<Person> personParents = personToDelete.getParents();
        if(!personParents.isEmpty()) {
            for (Person i : personParents) {
                i.getChildren().remove(personToDelete);
            }
            personToDelete.setParents(null);
        }
        List<Person> personCousins = personToDelete.getCousins();
        if(!personCousins.isEmpty()) {
            for (Person i : personCousins) {
                i.getCousins().remove(personToDelete);
            }
            personToDelete.setParents(null);
        }
        personRepo.deleteById(id);
    }

    public void updatePerson(long id, String name, Person.Sex sex , Date birthDate , Date deathDate, String biography) {
        Optional<Person> person = findById(id);

        if (!person.isPresent()) {
            throw new NoSuchElementException();
        }
        Person personToUpdate = person.get();
        if (name != null) personToUpdate.setName(name);
        if (sex != null) personToUpdate.setSex(sex);
        if (birthDate != null) personToUpdate.setBirthDate(birthDate);
        if (deathDate != null) personToUpdate.setDeathDate(deathDate);
        if (biography != null) personToUpdate.setBiography(biography);
        personRepo.save(personToUpdate);
    }

    @PostConstruct
    private void initPersons() throws ParseException {
        Person newPerson = new Person.Builder()
                .setName("Ullyses")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1950-05-12"))
                .setDeathDate(null)
                .setBiography(null)
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Nadin")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1954-04-13"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("German")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1952-01-01"))
                .setDeathDate(new SimpleDateFormat("yyyy-MM-dd").parse("1956-02-18"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Valerie")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1956-02-08"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Maurizio")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1974-11-10"))
                .setDeathDate(new SimpleDateFormat("yyyy-MM-dd").parse("1986-03-02"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Arman")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-04-04"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Virginia")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1977-02-01"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Felizia")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1979-08-13"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Charles")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1975-07-22"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Michelle")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1977-05-20"))
                .setDeathDate(new SimpleDateFormat("yyyy-MM-dd").parse("1995-03-12"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Frank")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1998-04-03"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Paul")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-13"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Diane")
                .setSex(Person.Sex.female)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1995-03-12"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Franzisk")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-23"))
                .build();
        personRepo.save(newPerson);
        newPerson = new Person.Builder()
                .setName("Virgile")
                .setSex(Person.Sex.male)
                .setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-23"))
                .build();
        personRepo.save(newPerson);
        System.out.println(newPerson.getSex() == Person.Sex.female);
    }
}
