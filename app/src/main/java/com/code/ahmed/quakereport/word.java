package com.code.ahmed.quakereport;

public class word {

    private String url;
    double power;
    String location;
    private long date;


    public word(double power,String location,long date,String url){
        this.power=power;
        this.location=location;
        this.date=date;
        this.url=url;
    }


    public double getPower(){
        return power;
    }
    public String getLocation(){
        return location;

    }
    public long getDate(){
        return date;
    }

    public String getUrl(){
        return url;
    }

}
