package grbn.springframework.petclinic.repositories;

import grbn.springframework.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
