import { Component, OnInit} from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { DetailsService } from 'src/app/services/details.service';
import { ActivatedRoute } from '@angular/router';
import { FutureChart } from 'src/app/models/FutureChart';

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.css']
})
export class PredictionComponent implements OnInit {

  dataPoints:any = [];
  chart:any;
  ticker: string;
 
  chartOptions = {
    theme: "light2",
    zoomEnabled: true,
    exportEnabled: true,
    title: {
      text:"Stock Price Prediction"
    },
    subtitles: [{
      text: "Loading Data...",
      fontSize: 24,
      horizontalAlign: "center",
      verticalAlign: "center",
      dockInsidePlotArea: true
    }],
    axisY: {
      title: "Opening Price (in USD)",
      prefix: "$"
    },
    data: [{
      type: "line",
      name: "Opening Price",
      yValueFormatString: "$#,###.00",
      xValueType: "dateTime",
      dataPoints: this.dataPoints
    }]
  }

  constructor(private detailService: DetailsService, private route: ActivatedRoute) {}

  // ngOnDestroy(){

  //   clearInterval(this.interval);
  // }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.ticker = params.get("ticker").toUpperCase();
      //this.ticker = 'AAPL'
      //let watchlist = JSON.parse(localStorage.getItem("watchlist"));

      // if (watchlist){
      //   this.isAddedToFav = watchlist[this.ticker] || false;
      // }
    })

    this.detailService.getPredictChart(this.ticker, 24).subscribe(res => {

      let data = res.results;
      for(let i = 0; i < data.length; i++){
        this.dataPoints.push({x: new Date(data[i].date), y: Number(data[i].open) });
      }
      this.chart.subtitles[0].remove();
    });
  }
 
  getChartInstance(chart: object) {
    this.chart = chart;
  }
  
}    