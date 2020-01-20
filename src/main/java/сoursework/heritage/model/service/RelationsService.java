package сoursework.heritage.model.service;

import org.springframework.stereotype.Service;
import сoursework.heritage.model.entity.Person;
import сoursework.heritage.model.repository.PersonRepo;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;

@Service
public class RelationsService {
    private final PersonRepo personRepo;
    RelationsService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getParents(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        return person.getParents();
    }

    public Iterable<Person> getChildren(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        return person.getChildren();
    }

    public Person getSpouse(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        return person.getSpouse();
    }

    public Iterable<Person> getCousins(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        return person.getCousins();
    }


    public void addParent(long personId, long parentId) {
        Optional<Person> personOptional = personRepo.findById(personId);
        Optional<Person> parentOptional = personRepo.findById(parentId);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        if (!parentOptional.isPresent()) {
            throw new NoSuchElementException("Parent does not exist");
        }
        Person person = personOptional.get();
        System.out.println(person);
        Person parent = parentOptional.get();
        System.out.println(parent);
        List<Person> personParents = person.getParents();

        List<Person> personDescendants = getDescendants(personId);
        if(personDescendants.contains(parent)) {
            throw new IllegalArgumentException("Person can`t be an ancestor to himself.");
        }

        System.out.println(personParents);
        if (personParents.size() >= 2) {
            throw new IllegalArgumentException("One person can only have two parents");
        }

        if(!personParents.isEmpty()) {
            for(Person i : personParents) {
                if(i.getSex() == parent.getSex()) {
                    throw new IllegalArgumentException("One-sex parents are not allowed");
                }
            }
        }
        personParents.add(parent);
        person.setParents(personParents);
        personRepo.save(person);
        System.out.println(person.getParents());

        List<Person> parentChildren = parent.getChildren();
        System.out.println(parent.getChildren());
        parentChildren.add(person);
        parent.setChildren(parentChildren);
        personRepo.save(parent);
        System.out.println(parent.getChildren());
        System.out.println("________________");

    }

    public void deleteParent(long childId, long parentId) {
        Optional<Person> personOptional = personRepo.findById(childId);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        List<Person> personParents = person.getParents();
        if(!personParents.isEmpty()) {
            for (Person i : personParents) {
                if(i.getId() == parentId) {
                    i.getChildren().remove(person);
                    personRepo.save(i);
                    personParents.remove(i);
                    person.setParents(personParents);
                    personRepo.save(person);
                    return;
                }
            }
        }
    }

//    public void addChild(long personId, long childId)
//    {
//        Optional<Person> personOptional = personRepo.findById(personId);
//        Optional<Person> childOptional = personRepo.findById(childId);
//        if (!personOptional.isPresent()) {
//            throw new NoSuchElementException("Person does not exist");
//        }
//        if (!childOptional.isPresent()) {
//            throw new NoSuchElementException("Parent does not exist");
//        }
//        Person person = personOptional.get();
//        System.out.println(person);
//        Person child = childOptional.get();
//        System.out.println(child);
//
//        List<Person> personChildren = person.getChildren();
//        System.out.println(personChildren);
//        personChildren.add(child);
//        person.setChildren(personChildren);
//        personRepo.save(person);
//        System.out.println(person.getChildren());
//        System.out.println("________________");
//        personRepo.save(child);
//    }


    public void addSpouse(long firstSpouseId, long secondSpouseId) {
        Optional<Person> firstSpouseOptional = personRepo.findById(firstSpouseId);
        Optional<Person> secondSpouseOptional = personRepo.findById(secondSpouseId);
        if (!firstSpouseOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        if (!secondSpouseOptional.isPresent()) {
            throw new NoSuchElementException("Parent does not exist");
        }
        Person firstSpouse = firstSpouseOptional.get();
        Person secondSpouse = secondSpouseOptional.get();
        System.out.println(firstSpouse);
        System.out.println(firstSpouse.getSpouse());
        System.out.println(secondSpouse);
        System.out.println(secondSpouse.getSpouse());
        firstSpouse.setSpouse(secondSpouse);
        secondSpouse.setSpouse(firstSpouse);

        personRepo.save(firstSpouse);
        personRepo.save(secondSpouse);
        System.out.println(firstSpouse.getSpouse());
        System.out.println(secondSpouse.getSpouse());
        System.out.println("_____________");
    }

    public void deleteSpouse(long firstSpouseId) {
        Optional<Person> personOptional = personRepo.findById(firstSpouseId);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        Person personSpouse = person.getSpouse();
        personSpouse.setSpouse(null);
        person.setSpouse(null);
        personRepo.save(person);
        personRepo.save(personSpouse);
    }

    public void addCousin(long firstCousinId, long secondCousinId) {
        Optional<Person> firstCousinOptional = personRepo.findById(firstCousinId);
        Optional<Person> secondCousinOptional = personRepo.findById(secondCousinId);
        if (!firstCousinOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        if (!secondCousinOptional.isPresent()) {
            throw new NoSuchElementException("Parent does not exist");
        }
        Person firstCousin = firstCousinOptional.get();
        Person secondCousin = secondCousinOptional.get();
        System.out.println(firstCousin);
        System.out.println(firstCousin.getCousins());
        System.out.println(secondCousin);
        System.out.println(secondCousin.getCousins());

        List<Person> firstCousinCousins = firstCousin.getCousins();
        firstCousinCousins.add(secondCousin);

        List<Person> secondCousinCousins = secondCousin.getCousins();
        secondCousinCousins.add(firstCousin);

        firstCousin.setCousins(firstCousinCousins);
        secondCousin.setCousins(secondCousinCousins);

        personRepo.save(firstCousin);
        personRepo.save(secondCousin);
        System.out.println(firstCousin.getCousins());
        System.out.println(secondCousin.getCousins());
        System.out.println("_____________");
    }

    public void deleteCousin(long childId, long parentId) {
        Optional<Person> personOptional = personRepo.findById(childId);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        List<Person> personCousins = person.getCousins();
        if(!personCousins.isEmpty()) {
            for (Person i : personCousins) {
                if(i.getId() == parentId) {
                    i.getCousins().remove(person);
                    personRepo.save(i);
                    personCousins.remove(i);
                    person.setCousins(personCousins);
                    personRepo.save(person);
                    return;
                }
            }
        }
    }

    public List<Person> getAncestors(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        List <Person> personParents = person.getParents();
        List<Person> ancestors = new ArrayList<>(person.getParents());
        for (Person i : personParents) {
            ancestors.addAll(getAncestors(i.getId()));
        }
        return ancestors;
    }

    public List<Person> getDescendants(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        List <Person> personChildren = person.getChildren();
        List<Person> descendants = new ArrayList<Person>(personChildren);
        for (Person i : personChildren) {
            descendants.addAll(getDescendants(i.getId()));
        }
        return descendants;
    }

    private void relativesRecursiveSearch(long id, List<Person> relatives, Set<Person> visited) {
        Person person = personRepo.findById(id).get();
        visited.add(person);

        List <Person> personParents = person.getParents();
        List <Person> personChildren = person.getChildren();
        System.out.println(relatives);
        System.out.println("__________________________");

        for(Person i : personParents) {
            if(!visited.contains(i)) {
                relatives.add(i);
                relativesRecursiveSearch(i.getId(), relatives, visited);
            }

        }
        for(Person i : personChildren) {
            if(!visited.contains(i)) {
                relatives.add(i);
                relativesRecursiveSearch(i.getId(), relatives, visited);
            }
        }
    }

    public List<Person> getRelatives(long id) {
        Optional<Person> personOptional = personRepo.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchElementException("Person does not exist");
        }
        Person person = personOptional.get();
        List<Person> relatives = new ArrayList<Person>();
        Set<Person> visited = new HashSet<>();
        relativesRecursiveSearch(id, relatives, visited);
        return relatives;
    }

    private String relationRecursiveSearch(Person person, Person targetPerson, Set<Person> visited) {
        visited.add(person);
        List <Person> personParents = person.getParents();
        List <Person> personChildren = person.getChildren();
        Person personSpouse = person.getSpouse();
        List <Person> personCousins = person.getCousins();
        if(personChildren!= null && personChildren.contains(targetPerson)) return person.getName() + " - parent of " + targetPerson.getName();
        if(personParents!= null && personParents.contains(targetPerson)) return person.getName() + " - child of " + targetPerson.getName();
        if(personSpouse!= null && personSpouse.equals(targetPerson)) return person.getName() + " - spouse of " + targetPerson.getName();
        if(personCousins!= null && personCousins.contains(targetPerson)) return person.getName() + " - cousin of " + targetPerson.getName();
        if(personCousins!= null )
            for (Person i : personCousins) {
                if(!visited.contains(i)) {
                    String result = relationRecursiveSearch(i, targetPerson, visited);
                    if(!result.isEmpty()) return person.getName() + " - cousins of " + i.getName() + ", " + result;
                }
            }
        if(personSpouse!= null && !visited.contains(personSpouse)) {
            String result = relationRecursiveSearch(personSpouse, targetPerson, visited);
            if(!result.isEmpty()) return person.getName() + " - spouse of " + personSpouse.getName() + ", " + result;
        }
        if(personParents!= null )
        for (Person i : personParents) {
            if(!visited.contains(i)) {
                String result = relationRecursiveSearch(i, targetPerson, visited);
                if(!result.isEmpty()) return person.getName() + " - child of " + i.getName() + ", " + result;
            }
        }
        if(personChildren!= null )
        for (Person i : personChildren) {
            if(!visited.contains(i)) {
                String result = relationRecursiveSearch(i, targetPerson, visited);
                if(!result.isEmpty()) return person.getName() + " - parent of " + i.getName() + ", " + result;
            }
        }
        return "";
    }

    public String getRelation(long id1, long id2) {
        Person firstPerson = personRepo.findById(id1).get();
        Person secondPerson = personRepo.findById(id2).get();
        Set<Person> visited = new HashSet<>();
        String relation = relationRecursiveSearch(firstPerson,secondPerson,visited);
        if(relation.equals("")) return "No relation between persons";
        return relation;
    }

    @PostConstruct
    private void initRelations() {
        addParent(5,1);
        addParent(6,1);
        addParent(5,2);
        addParent(6,2);
        addParent(7,3);
        addParent(8,3);
        addParent(7,4);
        addParent(8,4);
        addParent(11,6);
        addParent(12,6);
        addParent(11,7);
        addParent(12,7);
        addParent(13,9);
        addParent(13,10);
        addParent(14,12);
        addParent(15,12);
        addParent(14,13);
        addParent(15,13);

        addSpouse(1,2);
        addSpouse(3,4);
        addSpouse(6,7);
        addSpouse(9,10);
        addSpouse(12,13);

        addCousin(5,6);
        addCousin(7,8);
        addCousin(11,12);
        addCousin(14,15);

        System.out.println(getRelation(1,15));

    }
}
