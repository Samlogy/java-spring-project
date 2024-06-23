import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  helloText = '';

  constructor(private oauthService: OAuthService, private httpClient: HttpClient) { }

  logout() {
    this.oauthService.logOut();
  }

  getHelloAdmin() {
    this.httpClient.get<{ message: string }>('http://localhost:8080/hello-admin', {
      headers: {
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`
      }
    }).subscribe(result => {
      this.helloText = result.message;
    });
  }

  getHelloModerator() {
    this.httpClient.get<{ message: string }>('http://localhost:8080/hello-moderator', {
      headers: {
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`
      }
    }).subscribe(result => {
      this.helloText = result.message;
    });
  }

  getHelloBoth() {
    this.httpClient.get<{ message: string }>('http://localhost:8080/hello-both', {
      headers: {
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`
      }
    }).subscribe(result => {
      this.helloText = result.message;
    });
  }
}