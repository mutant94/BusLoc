package com.example.lukaszt.busloaction;

public enum Bus {
    A(1,111, 53.1292043,23.1459105, 0),
    B(1,222, 53.127028,23.1680336, 1),
    C(2,333, 53.1438426,23.1346509, 1),
    D(2,444, 53.1343299,23.1584475, 1),
    E(3,555, 53.1215416,23.1767682, 0);
    int numberLine;
    int numberBus;
    double lon;
    double lat;
    int option;
    Bus(int numberLine, int numberBus, double lat, double lon, int option){
        this.numberLine = numberLine;
        this.numberBus = numberBus;
        this.lat = lat;
        this.lon = lon;
        this.option = option;
    }

    public String getText(){
        return "L:" + numberLine + " N:"+ numberBus;
    }
    public int getOption(){
        return option;
    }
}
