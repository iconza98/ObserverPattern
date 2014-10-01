package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ikanisamani on 9/30/14.
 */
public class LocalStocks extends Subject {

    private ArrayList<Observer> observerList = new ArrayList<Observer>();
    private HashMap<String,TickerInfo> dataMap = new HashMap<String, TickerInfo>();
    private ArrayList<ArrayList<String>> tickerListList = new ArrayList<ArrayList<String>>();
    public String lastUpdateTime = "";           // change to private after testing


    @Override
    public void addObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
       for(Observer o : observerList){
           o.update(lastUpdateTime, dataMap);
       }
    }

    public void display() {
        for(Map.Entry<String,TickerInfo> h : dataMap.entrySet()){
            System.out.println(h.getKey() + " " + h.getValue());
        }
    }

    public void openFile(String fileName){
        String line = "";
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<ArrayList<String>> listlist = new ArrayList<ArrayList<String>>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    array.add(line);
                } else {

                    listlist.add(array);

                    array = new ArrayList<String>();
                    tickerListList = listlist;
                }
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        readAndUpdate();

    }
    private void readAndUpdate(){
        for(ArrayList<String> array : tickerListList) {
            if (array.size() > 0) {
                lastUpdateTime = array.get(0);
                array.remove(0);
                for (String s : array) {
                    parseTickerLine(s);
                }
                notifyObservers();
                dataMap.clear();
            }

        }
    }

    private void parseTickerLine(String line){
        String regex = "(\\D+) (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(line);
        String companyName ="";
        String tickerDataString = "";
        if (matcher.find()) {
            companyName = matcher.group(1);
            tickerDataString = matcher.group(2);

            TickerInfo test = new TickerInfo(companyName, tickerDataString);

            if(!dataMap.containsKey(companyName)) {
                dataMap.put(companyName, test);
            } else {
                dataMap.replace(companyName, test);
            }

        }

    }
}
