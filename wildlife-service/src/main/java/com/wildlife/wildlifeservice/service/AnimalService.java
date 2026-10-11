
package com.wildlife.wildlifeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.wildlifeservice.entity.Animal;
import com.wildlife.wildlifeservice.repository.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Get all animals
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Add a new animal
    public Animal addAnimal(Animal animal) {
        animal.setAnimalId(null);
        return animalRepository.save(animal);
    }

    // Get an animal by ID
    public Animal getAnimalById(Long id) {
        List<Animal> animals = animalRepository.findAll();

        for (Animal animal : animals) {
            if (animal.getAnimalId().equals(id)) {
                return animal;
            }
        }

        return null;
    }

    // Update an existing animal
    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        List<Animal> animals = animalRepository.findAll();

        for (Animal animal : animals) {
            if (animal.getAnimalId().equals(id)) {

                animal.setSpeciesName(updatedAnimal.getSpeciesName());
                animal.setIdentificationCode(
                    updatedAnimal.getIdentificationCode()
                );
                animal.setAge(updatedAnimal.getAge());
                animal.setHealthStatus(updatedAnimal.getHealthStatus());
                animal.setLocation(updatedAnimal.getLocation());

                return animalRepository.save(animal);
            }
        }

        return null;
    }

    // Delete an animal
    public boolean deleteAnimal(Long id) {
        List<Animal> animals = animalRepository.findAll();

        for (Animal animal : animals) {
            if (animal.getAnimalId().equals(id)) {
                animalRepository.delete(animal);
                return true;
            }
        }

        return false;
    }
}
