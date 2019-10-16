package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());

        return "index";
    }



    @GetMapping("/addperson")

    public String personForm(Model model){
        model.addAttribute("person", new Person());
        return "personform";
    }


    @PostMapping("/processperson")
    public String processPersonForm(@Valid Person person, BindingResult result){

        if (result.hasErrors()){
            return "personform"; }

        personRepository.save(person);

        return "redirect:/personlist";
    }


    @RequestMapping("/personlist")

    public String personList(Model model){

        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("pets",petRepository.findAll());
        return "personlist";
    }


    @GetMapping("/addpet")

    public String petsForm(Model model){

        model.addAttribute("persons", personRepository.findAll());

        model.addAttribute("pet", new Pet());

        return "petform";
    }


    @PostMapping("/processpet")

    public String processPetForm(@Valid Pet pet, BindingResult result){

        if (result.hasErrors()){

            return "petform";
        }
        petRepository.save(pet);

        return "redirect:/petlist";
    }


    @RequestMapping("/petlist")

    public String petList(Model model){

        model.addAttribute("pets", petRepository.findAll());

        return "petlist";
    }


    @RequestMapping("/detail_person/{id}")

    public String showPerson(@PathVariable("id") int id, Model model){

        model.addAttribute("person", personRepository.findById(id).get());
        return "showperson";
    }


    @RequestMapping("/update_person/{id}")

    public String updatePerson(@PathVariable("id") int id, Model model){

        model.addAttribute("person", personRepository.findById(id));

        return "personform";
    }


    @RequestMapping("/delete_person/{id}")

    public String delPerson(@PathVariable("id") int id){

        personRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/detail_pet/{id}")

    public String showPet(@PathVariable("id") int id, Model model){

        model.addAttribute("pet", petRepository.findById(id).get());

        return "showpet";

    }


    @RequestMapping("/update_pet/{id}")

    public String updatePet(@PathVariable("id") int id, Model model){

        model.addAttribute("pet", petRepository.findById(id).get());

        model.addAttribute("persons",personRepository.findAll());

        return "petform";

    }



    @RequestMapping("/delete_pet/{id}")

    public String delPet(@PathVariable("id") int id){

        petRepository.deleteById(id);

        return "redirect:/";

    }




}
