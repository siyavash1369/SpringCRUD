package com.datastore.datastore.controller;

import com.datastore.datastore.model.Person;
import com.datastore.datastore.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class PersonController {

    List<String> courses;
    @Autowired
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute
    public void preload() {
        courses  = new ArrayList<String>();
        courses.add("syavash");
        courses.add("ali");
        courses.add("sina");
    }

    @GetMapping("/signup")
    public String savePerson(Model model, Person person){
        model.addAttribute("courses", courses);
        return "register";
    }

    @PostMapping("/save")
    public String registerPerson(@Valid Person person, BindingResult result, Model model){
        if (result.hasErrors())
        {
            model.addAttribute("courses", courses);
            return "register";
        }
        personRepository.save(person);
        return "redirect:/account/welcome";
    }

    @GetMapping("/welcome")
    public String index(Model model){
        model.addAttribute("person", personRepository.findAll());
        return "welcome";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid person id:"+id));
        model.addAttribute("person", person);
        model.addAttribute("courses", courses);
        return "/updatePerson";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, @Valid Person person, BindingResult result, Model model){
        if(result.hasErrors())
        {
            person.setId(id);
            model.addAttribute("courses",courses);
            return "updatePerson";
        }
        personRepository.save(person);
        return "redirect:/account/welcome";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id, Model model){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid person id:"+id));
        personRepository.delete(person);
        return "redirect:/account/welcome";
    }

}
