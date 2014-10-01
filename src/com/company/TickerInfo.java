package com.company;

import java.util.DoubleSummaryStatistics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ikanisamani on 9/30/14.
 */
public class TickerInfo {
    public String company;
    public String tickerSymbol;
    public double currentPrice;
    public double changeMoney;
    public double changePercent;
    public double ytdPercent;
    public double high;
    public double low;
    public String peRatio; // could be a double

    public TickerInfo(String name, String info){
        this.company = name;

        // Get Ticker Symbol
        String coSymbol = "";
        String[] temp = name.split(" ");
        coSymbol = temp[temp.length -1];
        this.tickerSymbol = coSymbol;

        String regex = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(name);
        if (matcher.find()) {
            this.tickerSymbol = coSymbol;
        }
        info = info.replace("  "," ");
        String[] tickerData = info.split(" ");
        int i = 0;
        this.currentPrice = Double.parseDouble(tickerData[i++]);
        this.changeMoney = Double.parseDouble(tickerData[i++]);
        this.changePercent = Double.parseDouble(tickerData[i++]);
        this.ytdPercent = Double.parseDouble(tickerData[i++]);
        this.high = Double.parseDouble(tickerData[i++]);
        this.low = Double.parseDouble(tickerData[i++]);
        this.peRatio = tickerData[i];
    }
    @Override
    public String toString(){
        return company + " " + currentPrice + " " + changeMoney + " "
                + changePercent + " " + ytdPercent + " " + high + " "
                + low + " " + peRatio;

    }
}
