import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DashBoardComponent } from "./dash-board/dash-board.component";
import { TableComponent } from './table/table.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TableComponent,  HttpClientModule, DashBoardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
