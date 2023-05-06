import { Component, OnInit } from '@angular/core';
import { CompanySearchResult } from '../../models/CompanySearchResult';
import {Observable} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {SearchService} from 'src/app/services/search.service';
import {switchMap, debounceTime, tap, finalize, startWith} from 'rxjs/operators';
//import {User, IUserResponse} from 'src/app/models/Users';
import { tick } from '@angular/core/testing';
import { Router } from '@angular/router';
import { News } from 'src/app/models/News';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DetailsService } from 'src/app/services/details.service';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-ticker-search',
  templateUrl: './home-modal.component.html',
  styleUrls: ['./home-modal.component.css'],
  providers: [DatePipe]
})
export class HomeModalComponent implements OnInit {

  companySResults: CompanySearchResult[] = [];
  searchForm: FormGroup;
  isLoading = false;

  stockNews: News[];
  businessNews: News[];
  techNews: News[];
  currNews: News;
  myDate:string;
  

  
  constructor(private fb: FormBuilder, private appService: SearchService, private router: Router, private modalService: NgbModal, 
    private detailService: DetailsService, private datePipe: DatePipe) {}

  ngOnInit() {
    const today = new Date();
    this.myDate = this.datePipe.transform(today, 'yyyy-MM-dd');

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

      this.detailService.getTopNews("StockMarket").subscribe ( res => {

        this.stockNews = res.results;
  
      });

      this.detailService.getTopNews("Business").subscribe ( res => {

        this.businessNews = res.results;
  
      });

      this.detailService.getTopNews("Technology").subscribe ( res => {

        this.techNews = res.results;
  
      });
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


  open(news, content) {
    this.currNews = news;

    window.open(this.currNews.url, "_blank");

  }


}
