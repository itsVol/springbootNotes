package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@Api("人员管理")
@RequestMapping("api/v1/Person")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ApiOperation(value = "添加用户")
    public void addPerson(@Valid @NonNull  @RequestBody Person person){
         personService.addPerson(person);
    }

    @GetMapping
    @ApiOperation(value = "获取全部用户")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @ApiOperation(value = "通过id获取用户")
    @GetMapping(path ="{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping( path = "{id}")
    @ApiOperation(value="删除用户")
    public void deletePerson(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Person person){
        personService.updatePerson(id, person);
    }
}
