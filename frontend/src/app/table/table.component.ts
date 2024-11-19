import { Component, OnInit } from '@angular/core';
import { WorkPlace } from '../interfaces/work-place';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnInit  {

  workPlaces: WorkPlace[] = [
    {
      country: 'USA',
      name: 'New York Office',
      readings: 120,
      mediumAlert: '5',
      redAlert: '2'
    },
    {
      country: 'Germany',
      name: 'Berlin Office',
      readings: 90,
      mediumAlert: '3',
      redAlert: '1'
    },
    {
      country: 'Japan',
      name: 'Tokyo Office',
      readings: 150,
      mediumAlert: '8',
      redAlert: '5'
    },
    {
      country: 'Australia',
      name: 'Sydney Office',
      readings: 60,
      mediumAlert: '2',
      redAlert: '0'
    }
  ];

  constructor() {}

  ngOnInit(): void {}

}
