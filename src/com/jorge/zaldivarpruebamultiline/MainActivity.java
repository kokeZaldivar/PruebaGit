package com.jorge.zaldivarpruebamultiline;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.ManagerFactoryParameters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private StringBuffer codes = null;
	protected final static String[] dtcLetters = {"P","C","B","U"};
	private ArrayList<String> dtcsEncontrados;
	
	private Timer t;
	private int delayToCall;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Establece la actividad principal para el servicio
//	   pruebaService.ACTIVIDAD = this;
//	    
//	    // Intent del servicio
//	    Intent servicio = new Intent(this, pruebaService.class);
//
//	    // Inicio del servicio
//	    if(startService(servicio) == null){
//	    	Toast.makeText(this, "No se ha podido iniciar el servicio", 15000);
//	    }else{
//	    	Toast.makeText(this, "Servicio iniciado correctamente", 15000);
//	    }
        
//        dtcsEncontrados = new ArrayList<String>();
//        
//		TextView t1 = (TextView) findViewById(R.id.TextView01);
//		ejecutar();
//		
//		String res = formatResult();
//		t1.setText(res);
		
        delayToCall = 5000;
        MyCountDown counter = new MyCountDown(delayToCall, 1000);
        counter.start();
//		t = new Timer();
//		t.schedule(new discountOfProgressBar(),delayToCall,1000);
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	
    	 // Intent el servicio
	    Intent servicio = new Intent(this, pruebaService.class);
	    
	    // Para el servicio
	    if(stopService(servicio)){
	    	Toast.makeText(this, "Servicio finalizado correctamente", 15000);
	    }else{
	    	Toast.makeText(this, "No se ha podido finalizar el servicio", 15000);
	    }
    }
    
    
    //---------------------------------- Funciones a probar -------------------
	public void ejecutar() {
		int k = 2;
        for (int i = 0; i < 5; i++) {
                String res = "43 01 08 01 13 01 18 FD 48 6B D1 43 01 22 02 01 02 02 F1 48 6B D1 43 02 03 03 51 03 52 75 48 6B D1 43 03 53 11 08 15 52 9D 48 6B D1 43 16 14 16 32 00 00 \r\r".replace(" ","");
                String[] ress = res.split("\r");
                res = ress[0];
                
                if(k > 2){
                	k += 10;
                }
                for (int j = 0; j < 3; j++) {
//                    String byte1 = res.substring(2+j*6,4+j*6);
//                    String byte2 = res.substring(5+j*6,7+j*6);
                    String byte1 = res.substring(k,k+2);
                    String byte2 = res.substring(k+2,k+4);
                        int b1 = Integer.parseInt(byte1,16);
                        int b2 = Integer.parseInt(byte2,16);
                        
                        int val = b1 & 0xC0;
                        int val2 = (val)>> 6;
                		
                		if(b1+b2 != 0){
	                		String code = dtcLetters[val2];
	                		
	                		dtcsEncontrados.add(code+byte1+byte2);
                		}
                        
                		k += 4;
                        
                }
        }
	}
	
	public String formatResult() {
		return dtcsEncontrados.toString();
	}
	
	
	class MyCountDown extends CountDownTimer{

		public MyCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			finish();
		}

		@Override
		public void onTick(long arg0) {
        	//delayToCall = delayToCall - 1000;
        	
        		//progressDialogHandler.sendEmptyMessage(0);
       			TextView t1 = (TextView) findViewById(R.id.TextView01);
       			t1.setText(arg0+"");
        	

		}
		
	}
	
   	private Handler progressDialogHandler = new Handler(){
   		@Override
   		public void handleMessage(Message msg) {
   			TextView t1 = (TextView) findViewById(R.id.TextView01);
   			t1.setText(delayToCall);
   		};
   	};
	
}