package org.springframework.samples.petclinic.model;

import java.util.Comparator;

public class PetComparator implements Comparator<Pet> {

	@Override
	public int compare(Pet pet1, Pet pet2) {
		String name1 = pet1.getName();
		String name2 = pet2.getName();
		return name1.compareTo(name2);
	}

}
