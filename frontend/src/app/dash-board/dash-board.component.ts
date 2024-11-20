import { Component } from '@angular/core';
import { TableComponent } from "../table/table.component";
import { CardData } from '../interfaces/card-data';
import { CardService } from '../services/card-service';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from "../sidebar/sidebar.component";

@Component({
  selector: 'app-dash-board',
  standalone: true,
  imports: [TableComponent, CommonModule, SidebarComponent],
  templateUrl: './dash-board.component.html',
  styleUrl: './dash-board.component.css'
})
export class DashBoardComponent {

  cards: CardData[] = [];
  cardsDown: CardData[] = [];

  constructor(private cardService: CardService) {
   }

   ngOnInit(): void {
    this.cardService.getCardsUp().subscribe((data: CardData[]) => {
      this.cards = data;
    });

    this.cardService.getCardsDown().subscribe(data => {
      this.cardsDown = data;
    });
  }



}
