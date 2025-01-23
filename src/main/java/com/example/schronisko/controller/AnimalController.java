package com.example.schronisko.controller;

import com.example.schronisko.model.Animal;
import com.example.schronisko.model.AnimalHistory;
import com.example.schronisko.repository.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @GetMapping
    public String listAnimals(Model model) {
        List<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        model.addAttribute("formatter", formatter);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("statuses", List.of("Do adopcji", "Zaadoptowany", "W trakcie leczenia"));
        model.addAttribute("speciesList", List.of("Pies", "Kot", "Królik", "Ptak"));
        return "create";
    }

    @PostMapping("/create")
    public String createAnimal(@ModelAttribute Animal animal) {
        animal.setDateAdded(LocalDateTime.now());
        animalRepository.save(animal);

        AnimalHistory history = new AnimalHistory();
        history.setAnimal(animal);
        history.setEvent("Przyjęcie do schroniska");
        history.setDate(LocalDateTime.now());
        animal.getHistory().add(history);

        animalRepository.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid animal ID: " + id));
        model.addAttribute("animal", animal);
        model.addAttribute("statuses", List.of("Do adopcji", "Zaadoptowany", "W trakcie leczenia"));
        model.addAttribute("speciesList", List.of("Pies", "Kot", "Królik", "Ptak"));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateAnimal(@PathVariable Long id, @ModelAttribute Animal animal) {
        Animal existingAnimal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid animal ID: " + id));

        if (!existingAnimal.getStatus().equals(animal.getStatus())) {
            AnimalHistory history = new AnimalHistory();
            history.setAnimal(existingAnimal);
            history.setEvent("Zmieniono status na: " + animal.getStatus());
            history.setDate(LocalDateTime.now());
            existingAnimal.getHistory().add(history);
        }

        existingAnimal.setName(animal.getName());
        existingAnimal.setSpecies(animal.getSpecies());
        existingAnimal.setAge(animal.getAge());
        existingAnimal.setStatus(animal.getStatus());

        animalRepository.save(existingAnimal);
        return "redirect:/animals";
    }

    @GetMapping("/view/{id}")
    public String viewAnimal(@PathVariable Long id, Model model) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid animal ID: " + id));
        model.addAttribute("animal", animal);
        model.addAttribute("formatter", formatter);
        return "view";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
        return "redirect:/animals";
    }
}
