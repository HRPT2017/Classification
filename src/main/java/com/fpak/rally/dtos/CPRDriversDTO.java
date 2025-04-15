package com.fpak.rally.dtos;

import java.util.List;

public class CPRDriversDTO {
     private int position;
    private int number;
    private String name;
    private int total;
    private List<CPRStagePointsDTO> stagePoints ;

    public CPRDriversDTO(int position, int number, String name, int total, List<CPRStagePointsDTO> stagePoints) {
        this.position = position;
        this.number = number;
        this.name = name;
        this.total = total;
        this.stagePoints = stagePoints;
    }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public List<CPRStagePointsDTO> getStagePoints() { return stagePoints; }
    public void setStagePoints(List<CPRStagePointsDTO> stagePoints) { this.stagePoints = stagePoints; }
    
}
