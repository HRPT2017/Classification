package com.fpak.velocity.dtos;

import com.fpak.velocity.dtos.RacePointsDTO;

import java.util.List;

public class VelocityClassificationDTO {
    private int position;
    private int number;
    private String name;
    private int total;
    private List<RacePointsDTO>  race1 ;
    private List<RacePointsDTO>  race2 ;

    public VelocityClassificationDTO(int position, int number, String name, int total, List<RacePointsDTO> race1, List<RacePointsDTO> race2) {
        this.position = position;
        this.number = number;
        this.name = name;
        this.total = total;
        this.race1 = race1;
        this.race2 = race2;
    }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public List<RacePointsDTO> getRace1() { return race1; }
    public void setRace1(List<RacePointsDTO> race1) { this.race1 = race1; }

    public List<RacePointsDTO> getRace2() { return race2; }
    public void setRace2(List<RacePointsDTO> race2) { this.race2 = race2; }
}
