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
public class PriceChangeReport extends Observer {

    private String time;
    private ArrayList<String> data = new ArrayList<String>();

    public void update(String upDatedTime, HashMap<String,TickerInfo> info){
        final double PERCENTAGE = 10.0;

        Map<String, TickerInfo> sortedMap = new TreeMap<String, TickerInfo>(info);

        time = upDatedTime;

        for(Map.Entry<String,TickerInfo> h : sortedMap.entrySet()){
            if(Math.abs(h.getValue().changePercent) >= PERCENTAGE){
                //System.out.println(h.getValue());
                data.add(h.getValue().toString());
            }
        }

        printToFile();

    }
    public void printToFile(){

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("PriceChangeReport.txt", true)));

            out.write(time + "\n");
            for(String s: data){
                out.write(s + "\n");
            }
            out.write("\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // clear arraylist
        data = new ArrayList<String>();
    }

}