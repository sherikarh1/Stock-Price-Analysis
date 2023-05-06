import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { filter } from 'rxjs/operators';

import { Alert, AlertType } from 'src/app/models/Alert';

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  private subject = new Subject<Alert>();
  private defaultId = 'default-alert';

  watchlistTimer;
  buyTimer;

  constructor() { }


  showWatchlistAlert(isAdded: boolean, ticker: string){

    clearTimeout(this.watchlistTimer);

    let alertType = "alert-success";
    let alertText = ticker + " added to Watchlist";

    if (!isAdded) {
      alertType = "alert-danger";
      alertText = ticker + " removed from Watchlist"
    }

    document.querySelector("#watchlist-alert").innerHTML = (
    '<div class="text-center alert alert-dismissible fade show ' + alertType + '" role="alert">' +
      alertText + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"> \
        <span aria-hidden="true">&times;</span> \
      </button> \
    </div>');

    this.watchlistTimer = setTimeout(function () { 
  
      // Closing the alert 
      //(<any>$('#watchlist-alert .' + alertType)).alert('close'); 
    }, 5000); 
  }


  showBuyAlert(ticker: string){

    if (!document.querySelector("#buy-alert")){

      // console.log("not alert");
      return;
    }

    clearTimeout(this.buyTimer);

    let alertType = "alert-success";
    let alertText = ticker + " bought sucessfully!";


    document.querySelector("#buy-alert").innerHTML = (
    '<div class="text-center alert alert-dismissible fade show ' + alertType + '" role="alert">' +
      alertText + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"> \
        <span aria-hidden="true">&times;</span> \
      </button> \
    </div>');

    this.buyTimer = setTimeout(function () { 
  
      // Closing the alert 
      //(<any>$('#buy-alert .' + alertType)).alert('close'); 
    }, 5000); 
  }

  
  // enable subscribing to alerts observable
  onAlert(id = this.defaultId): Observable<Alert> {
    return this.subject.asObservable().pipe(filter(x => x && x.id === id));
  }

  // convenience methods
  success(message: string, options?: any) {
    this.alert(new Alert({ ...options, type: AlertType.Success, message }));
  }

  error(message: string, options?: any) {
    this.alert(new Alert({ ...options, type: AlertType.Error, message }));
  }

  info(message: string, options?: any) {
    this.alert(new Alert({ ...options, type: AlertType.Info, message }));
  }

  warn(message: string, options?: any) {
    this.alert(new Alert({ ...options, type: AlertType.Warning, message }));
  }

  // main alert method    
  alert(alert: Alert) {
    alert.id = alert.id || this.defaultId;
    this.subject.next(alert);
  }

  // clear alerts
  clear(id = this.defaultId) {
    this.subject.next(new Alert({ id }));
  }


}
