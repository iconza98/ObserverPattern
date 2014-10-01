package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ikanisamani on 9/30/14.
 */
public class AverageReport extends Observer {

    private String time;
    private String data;

    public void update(String upDatedTime, HashMap<String,TickerInfo> info){

        time = upDatedTime;
        Map<String, TickerInfo> sortedMap = new TreeMap<String, TickerInfo>(info);

        double total = info.size();
        double numerator = 0.0;

        for(Map.Entry<String,TickerInfo> h : sortedMap.entrySet()){
            numerator += h.getValue().currentPrice;
        }
        data = " , " + "Average price: " + (numerator / total);

        printToFile();

    }
    public void printToFile(){

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("AverageReport.txt", true)));

            out.write(time + " ");
            out.write(data + "\n");
            out.write("\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // clear data for new update
        data = "";
    }

}
