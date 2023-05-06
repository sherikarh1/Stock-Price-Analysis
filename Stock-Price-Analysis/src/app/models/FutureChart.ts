export class FutureChart {

    constructor(public date: number, public open: string) {
        this.date = new Date(date).getTime();
    }
}

export interface FutureChartResponse {
    total: Number;
    results: FutureChart[];
}