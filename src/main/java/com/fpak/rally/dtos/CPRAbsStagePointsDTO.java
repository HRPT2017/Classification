package com.fpak.rally.dtos;

public class CPRAbsStagePointsDTO {
    private String stage;
    private String powerStage;
    private String geral;

    public CPRAbsStagePointsDTO(String stage, String powerStage, String geral) {
        this.stage = stage;
        this.powerStage = powerStage;
        this.geral = geral;
   
    }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public String getpowerStage() { return powerStage; }
    public void setpowerStage(String powerStage) { this.powerStage = powerStage; }

    public String getGeral() { return geral; }
    public void setGeral(String geral) { this.geral = geral; }

}