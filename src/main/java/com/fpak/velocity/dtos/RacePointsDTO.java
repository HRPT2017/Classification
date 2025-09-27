package com.fpak.velocity.dtos;

public class RacePointsDTO {
    private String tc;
    private String race;
    private String fastestLap;
    private String total;

    public RacePointsDTO(String tc, String race, String fastestLap, String total) {
        this.tc = tc;
        this.race = race;
        this.fastestLap = fastestLap;
        this.total= total;

    }

    public String getTC() { return tc; }
    public void setTC(String tc) { this.tc = tc; }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public String getFastestLap() {return fastestLap;}
    public void  setFastestLap(String fastestLap){this.fastestLap = fastestLap;}

    public String getTotal() {return total;}
    public void  setTotal(String total){this.total = total;}

}
