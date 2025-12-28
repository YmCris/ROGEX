import { Component, OnInit } from '@angular/core';
import { User } from '../../models/users/user';

@Component({
  selector: 'app-shop',
  imports: [],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop implements OnInit {

  user!: User;

  ngOnInit(): void {
    const user = JSON.parse(localStorage.getItem('user')!);
  }


}
