import { Component, OnInit } from '@angular/core';
import { Visit } from '../../models/visit.model';
import { VisitService } from '../../services/visit.service';

@Component({
  selector: 'app-visits-list',
  template: `
    <div class="container">
      <h2>Visits</h2>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>Date</th>
              <th>Description</th>
              <th>Pet</th>
              <th>Owner</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let visit of visits">
              <td>{{ visit.date | date: 'mediumDate' }}</td>
              <td>{{ visit.description }}</td>
              <td *ngIf="visit.pet">{{ visit.pet.name }}</td>
              <td *ngIf="!visit.pet">Unknown</td>
              <td *ngIf="visit.pet && visit.pet.owner">{{ visit.pet.owner.firstName }} {{ visit.pet.owner.lastName }}</td>
              <td *ngIf="!visit.pet || !visit.pet.owner">Unknown</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  `,
  styles: []
})
export class VisitsListComponent implements OnInit {
  visits: Visit[] = [];

  constructor(private visitService: VisitService) {}

  ngOnInit(): void {
    this.loadVisits();
  }

  loadVisits(): void {
    this.visitService.getVisits().subscribe(visits => {
      this.visits = visits;
    });
  }
}
