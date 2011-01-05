package com.jorge.zaldivarpruebamultiline;


public class ShareDataPruebas {
	// Definicion de Singleton
    private static ShareDataPruebas INSTANCE = null;
    
    private String callEmergency;
    
    private ShareDataPruebas() {
    	callEmergency = "";
    }
    
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new ShareDataPruebas();
        }
    }
    
    public static ShareDataPruebas getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    
    public String getCallEmergency(){
    	return this.callEmergency;
    }
    
    public void setCallEmergency(String res){
    	this.callEmergency = res;
    }
}
