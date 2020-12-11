package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author itsVol
 */
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DATABASE =new ArrayList<>();

    /**
     *
     * @param id 用户id
     * @param person 人
     * @return 1
     */
    @Override
    public int insertPerson(UUID id, Person person) {
        DATABASE.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DATABASE;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DATABASE.stream()
                        .filter(person -> person.getId().equals(id))
                        .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()){
            return 0;
        }
        DATABASE.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate =DATABASE.indexOf(person);
                    if (indexOfPersonToUpdate >=0){
                         DATABASE.set(indexOfPersonToUpdate,new Person(id,update.getName() ));
                         return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
