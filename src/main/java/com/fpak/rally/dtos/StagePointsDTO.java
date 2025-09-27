package com.fpak.rally.dtos;

public class StagePointsDTO {
    private String powerStage;
    private String geral;

    public StagePointsDTO(String powerStage, String geral) {
        this.powerStage = powerStage;
        this.geral = geral;
   
    }

    public String getPowerStage() { return powerStage; }
    public void setPowerStage(String powerStage) { this.powerStage = powerStage; }

    public String getGeral() { return geral; }
    public void setGeral(String geral) { this.geral = geral; }

}