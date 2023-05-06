import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatProgressSpinnerModule }  from '@angular/material/progress-spinner'
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs'
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {RouterModule, Routes } from "@angular/router";
import stock from 'highcharts/modules/stock.src';

import { AppComponent } from './app.component';
import { DetailsComponent } from './components/details/details.component';
import { TickerSearchComponent } from './components/ticker-search/ticker-search.component';
import { HighchartsChartModule } from 'highcharts-angular';
//import { NewsModalComponent } from './components/news-modal/news-modal.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BuyModalComponent } from './components/buy-modal/buy-modal.component';
import { NewsModalComponent } from './components/news-modal/news-modal.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { HomeModalComponent } from './components/home-modal/home-modal.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
//import { BuyModalComponent } from './components/buy-modal/buy-modal.component';

import { authInterceptorProviders } from './helper/auth.interceptor';
import { PredictionComponent } from './components/prediction/prediction.component';

import * as CanvasJSAngularChart from '../assets/canvasjs.angular.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';
var CanvasJSChart = CanvasJSAngularChart.CanvasJSChart;

@NgModule({
  declarations: [
    AppComponent,
    DetailsComponent,
    TickerSearchComponent,
    BuyModalComponent,
    NewsModalComponent,
    PortfolioComponent,
    WatchlistComponent,
    HomeModalComponent,
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
    PredictionComponent,
    CanvasJSChart,
    ChatbotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatFormFieldModule, 
    MatIconModule,
    MatAutocompleteModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatTabsModule,
    HighchartsChartModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
