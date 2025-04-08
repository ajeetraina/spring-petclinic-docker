import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pet } from '../../models/pet.model';
import { PetService } from '../../services/pet.service';

@Component({
  selector: 'app-pets-list',
  template: `
    <div class="container">
      <h2>Pets</h2>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>Name</th>
              <th>Birth Date</th>
              <th>Type</th>
              <th>Owner</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let pet of pets">
              <td>{{ pet.name }}</td>
              <td>{{ pet.birthDate | date: 'mediumDate' }}</td>
              <td>{{ pet.type.name }}</td>
              <td *ngIf="pet.owner">{{ pet.owner.firstName }} {{ pet.owner.lastName }}</td>
              <td *ngIf="!pet.owner">Unknown</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  `,
  styles: []
})
export class PetsListComponent implements OnInit {
  pets: Pet[] = [];

  constructor(
    private petService: PetService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPets();
  }

  loadPets(): void {
    this.petService.getPets().subscribe(pets => {
      this.pets = pets;
    });
  }
}
