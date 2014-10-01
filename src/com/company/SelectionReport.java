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
public class SelectionReport extends Observer {

    private String time;
    private ArrayList<String> data = new ArrayList<String>();

    public void update(String upDatedTime, HashMap<String,TickerInfo> info){


        Map<String, TickerInfo> sortedMap = new TreeMap<String, TickerInfo>(info);

        time = upDatedTime;
        String checkSymbol;
        for(Map.Entry<String,TickerInfo> h : sortedMap.entrySet()){
            checkSymbol =  h.getValue().tickerSymbol;
            if(checkSymbol.equals("ALL") || checkSymbol.equals("BA") || checkSymbol.equals("BC")
                    || checkSymbol.equals("GBEL") || checkSymbol.equals("KFT") || checkSymbol.equals("MCD")
                    || checkSymbol.equals("TR") || checkSymbol.equals("WAG")){
                data.add(h.getValue().toString());
            }
        }
        printToFile();

    }
    public void printToFile(){

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("SelectionReport.txt", true)));

            out.write(time + "\n");
            for(String s: data){
                out.write(s + "\n");
            }
            out.write("\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // clear data for new update
        data = new ArrayList<String>();
    }

}