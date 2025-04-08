package org.springframework.samples.petclinic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class VisitRestController {

    private final ClinicService clinicService;

    @Autowired
    public VisitRestController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/visits")
    public ResponseEntity<List<Visit>> getAllVisits() {
        List<Visit> visits = this.clinicService.findAllVisits();
        if (visits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @GetMapping("/visits/{visitId}")
    public ResponseEntity<Visit> getVisit(@PathVariable("visitId") int visitId) {
        Visit visit = this.clinicService.findVisitById(visitId);
        if (visit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits")
    public ResponseEntity<Visit> addVisit(@PathVariable("petId") int petId, @RequestBody Visit visit) {
        Pet pet = this.clinicService.findPetById(petId);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pet.addVisit(visit);
        this.clinicService.saveVisit(visit);
        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @PutMapping("/visits/{visitId}")
    public ResponseEntity<Visit> updateVisit(@PathVariable("visitId") int visitId, @RequestBody Visit visit) {
        Visit currentVisit = this.clinicService.findVisitById(visitId);
        if (currentVisit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentVisit.setDate(visit.getDate());
        currentVisit.setDescription(visit.getDescription());
        this.clinicService.saveVisit(currentVisit);
        return new ResponseEntity<>(currentVisit, HttpStatus.OK);
    }

    @DeleteMapping("/visits/{visitId}")
    public ResponseEntity<Void> deleteVisit(@PathVariable("visitId") int visitId) {
        Visit visit = this.clinicService.findVisitById(visitId);
        if (visit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.clinicService.deleteVisit(visit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
