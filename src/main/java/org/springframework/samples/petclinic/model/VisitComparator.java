package org.springframework.samples.petclinic.model;

import java.util.Comparator;

public class VisitComparator implements Comparator<Visit> {

	@Override
	public int compare(Visit visit1, Visit visit2) {
		return visit1.getDate().compareTo(visit2.getDate());
	}

}
