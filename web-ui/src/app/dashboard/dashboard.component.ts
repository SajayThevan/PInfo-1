import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(public keycloak: KeycloakService) { }

  ngOnInit() {
  }

}
