import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pet } from '../../models/pet.model';
import { PetService } from '../../services/pet.service';

@Component({
  selector: 'app-pet-detail',
  template: `
    <div class="container" *ngIf="pet">
      <h2>Pet Information</h2>
      <div class="card mb-4">
        <div class="card-header">Pet Details</div>
        <div class="card-body">
          <p><strong>Name:</strong> {{ pet.name }}</p>
          <p><strong>Birth Date:</strong> {{ pet.birthDate | date: 'mediumDate' }}</p>
          <p><strong>Type:</strong> {{ pet.type.name }}</p>
          <p *ngIf="pet.owner"><strong>Owner:</strong> {{ pet.owner.firstName }} {{ pet.owner.lastName }}</p>
        </div>
      </div>

      <h3>Visits</h3>
      <div class="table-responsive" *ngIf="pet.visits && pet.visits.length > 0">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Date</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let visit of pet.visits">
              <td>{{ visit.date | date: 'mediumDate' }}</td>
              <td>{{ visit.description }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div *ngIf="!pet.visits || pet.visits.length === 0" class="alert alert-info">
        No visit records for {{ pet.name }}.
      </div>

      <div class="mt-4">
        <button class="btn btn-secondary" (click)="goBack()">Back</button>
      </div>
    </div>
  `,
  styles: []
})
export class PetDetailComponent implements OnInit {
  pet: Pet | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private petService: PetService
  ) {}

  ngOnInit(): void {
    this.loadPet();
  }

  loadPet(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.petService.getPetById(+id).subscribe(pet => {
        this.pet = pet;
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/pets']);
  }
}
