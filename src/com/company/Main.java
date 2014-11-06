package com.company;


public class Main {

    public static void main(String[] args) {

        AverageReport avReporter = new AverageReport();
        SelectionReport selReporter = new SelectionReport();
        PriceChangeReport priceReporter = new PriceChangeReport();

        LocalStocks ls = new LocalStocks();
        ls.addObserver(avReporter);
        ls.addObserver(selReporter);
        ls.addObserver(priceReporter);

        System.out.println("Program will now parse Ticker data");
        if(args.length == 1){
            ls.openFile(args[0]);
        } else {
            ls.openFile("./Ticker.dat");
        }

        System.out.println("End of Program");

    }
}
