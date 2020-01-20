package сoursework.heritage.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import сoursework.heritage.model.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
    List<Person> findAll();
    Optional<Person> findById(long id);
    List<Person> findChildrenById(long id);
    Set<Person> findParentsById(long id);
}
