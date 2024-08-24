package grbn.springframework.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grbn.springframework.petclinic.ControllerTests;
import grbn.springframework.petclinic.controllers.VetController;
import grbn.springframework.petclinic.fauxspring.Model;
import grbn.springframework.petclinic.fauxspring.ModelMapImpl;
import grbn.springframework.petclinic.model.Vet;
import grbn.springframework.petclinic.services.SpecialtyService;
import grbn.springframework.petclinic.services.VetService;
import grbn.springframework.petclinic.services.map.SpecialityMapService;
import grbn.springframework.petclinic.services.map.VetMapService;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    VetService vetService;
    SpecialtyService specialtyService;

    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "joe", "buck", null);
        Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapImpl();

        String view = vetController.listVets(model);

        assertThat("vets/index").isEqualTo(view);

        Set modelAttribute = (Set) ((ModelMapImpl) model).getMap().get("vets");

        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}