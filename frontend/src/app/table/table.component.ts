import { Component, OnInit } from '@angular/core';
import { WorkPlace } from '../interfaces/work-place';
import { CommonModule } from '@angular/common';
import {WorkPlaceService} from '../services/work-place.service';
import {MatDialog} from '@angular/material/dialog';
import {InsertWorkPlaceComponent} from '../insert-work-place/insert-work-place.component';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnInit  {

  workPlaces: WorkPlace[] = [];

  constructor(
    private plantService: WorkPlaceService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.fetchPlants();
  }

  fetchPlants(): void {
    this.plantService.getPlants().subscribe({
      next: (data) => {
        this.workPlaces = data;
        console.log('Plantas obtenidas:', data);
      },
      error: (error) => {
        console.error('Error al obtener plantas:', error);
      },
    });
  }

  openInsertPlantModal() {
    const dialogRef = this.dialog.open(InsertWorkPlaceComponent, {
      width: '500px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(() => {
      this.fetchPlants();
    });
  }

}
