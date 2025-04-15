package com.fpak.rally.dtos;

public class CPRStagePointsDTO {
    private String stage;
    private String geral;

    public CPRStagePointsDTO(String stage, String geral) {
        this.stage = stage;
        this.geral = geral;
   
    }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public String getGeral() { return geral; }
    public void setGeral(String geral) { this.geral = geral; }
    
}
