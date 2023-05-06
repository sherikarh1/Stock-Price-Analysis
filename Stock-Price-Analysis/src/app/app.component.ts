import { Component, OnInit } from '@angular/core';
import { CompanySearchResult } from './models/CompanySearchResult';
import {Observable} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {SearchService} from 'src/app/services/search.service';
import {switchMap, debounceTime, tap, finalize, startWith} from 'rxjs/operators';
//import {User, IUserResponse} from 'src/app/models/Users';
import { tick } from '@angular/core/testing';
import { Router } from '@angular/router';
import { TokenStorageService } from './services/token-storage.service';
//import { MDBBootstrapModule } from 'angular-bootstrap-md';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Stock-Price-Analysis';

  private roles: string[];
  isLoggedIn = false;
  //showAdminBoard = false;
  //showModeratorBoard = false;
  username: string;

  companySResults: CompanySearchResult[] = [];
  searchForm: FormGroup;
  isLoading = false;

  constructor(private fb: FormBuilder, private tokenStorageService: TokenStorageService, private appService: SearchService, private router: Router) {}

  ngOnInit() {


    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      //this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      //this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }


    this.searchForm = this.fb.group({
      userInput: null
    })
      this.searchForm
      .get('userInput')
      .valueChanges
      .pipe(
        startWith(''),
        debounceTime(300),
        tap(() => this.isLoading = true),
        switchMap(value => this.appService.search({name: value})
        .pipe(
          finalize(() => this.isLoading = false),
          )
        )
      )
      .subscribe(companys => this.companySResults = companys.results);
  }

  displayFn(company: CompanySearchResult) {
    if (company) { 
      return company.ticker; 
    }
  }

  onSubmit(tickerVal: string) {
    if (tickerVal.length == 0){
      return;
    }

    this.router.navigateByUrl('details/' + tickerVal);    
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
