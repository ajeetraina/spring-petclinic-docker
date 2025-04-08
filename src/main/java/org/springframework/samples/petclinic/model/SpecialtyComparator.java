package org.springframework.samples.petclinic.model;

import java.util.Comparator;

public class SpecialtyComparator implements Comparator<Specialty> {

	@Override
	public int compare(Specialty specialty1, Specialty specialty2) {
		return specialty1.getName().compareTo(specialty2.getName());
	}

}
