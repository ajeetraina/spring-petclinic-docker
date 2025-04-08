package org.springframework.samples.petclinic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Owner;
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
@RequestMapping("/api/owners")
public class OwnerRestController {

	private final ClinicService clinicService;

	@Autowired
	public OwnerRestController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Owner>> getAllOwners() {
		List<Owner> owners = this.clinicService.findAllOwners();
		if (owners.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owners, HttpStatus.OK);
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
		this.clinicService.saveOwner(owner);
		return new ResponseEntity<>(owner, HttpStatus.CREATED);
	}

	@PutMapping("/{ownerId}")
	public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") int ownerId, @RequestBody Owner owner) {
		Owner currentOwner = this.clinicService.findOwnerById(ownerId);
		if (currentOwner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentOwner.setFirstName(owner.getFirstName());
		currentOwner.setLastName(owner.getLastName());
		currentOwner.setAddress(owner.getAddress());
		currentOwner.setCity(owner.getCity());
		currentOwner.setTelephone(owner.getTelephone());
		this.clinicService.saveOwner(currentOwner);
		return new ResponseEntity<>(currentOwner, HttpStatus.OK);
	}

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.clinicService.deleteOwner(owner);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
