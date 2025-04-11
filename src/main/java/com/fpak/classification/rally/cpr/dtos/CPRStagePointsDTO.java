package com.fpak.classification.rally.cpr.dtos;

public class CPRStagePointsDTO {
    private String stage;
    private String pS;
    private String geral;

    public CPRStagePointsDTO(String stage, String pS, String geral) {
        this.stage = stage;
        this.pS = pS;
        this.geral = geral;
   
    }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public String getPS() { return pS; }
    public void setPS(String pS) { this.pS = pS; }

    public String getGeral() { return geral; }
    public void setGeral(String geral) { this.geral = geral; }

}