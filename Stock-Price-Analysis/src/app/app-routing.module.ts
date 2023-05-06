import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from 'src/app/components/details/details.component';
import { HomeModalComponent } from './components/home-modal/home-modal.component';
import { LoginComponent } from './components/login/login.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { RegisterComponent } from './components/register/register.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { PredictionComponent } from './components/prediction/prediction.component';
import { TickerSearchComponent } from './components/ticker-search/ticker-search.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';

const routes: Routes = [
  { path: '', component: HomeModalComponent },
  { path: 'details/:ticker', component: DetailsComponent },
  { path: 'search', component: TickerSearchComponent },
  { path: 'chat', component: ChatbotComponent },
  { path: 'prediction/:ticker', component: PredictionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent },
  { path: 'watchlist', component: WatchlistComponent },
  { path: 'portfolio', component: PortfolioComponent },
  { path: '**',   redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
