package com.jorge.zaldivarpruebamultiline;

import java.util.ArrayList;





import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class pruebaService extends Service {
	// Actividad principal del servicio
	public static Activity ACTIVIDAD;
	
	int cont;
	boolean stop;
	
	ListView l1;
	
	ArrayList<String> array1;
	
	Thread t1;
	ShareDataPruebas singleton;
	
	@Override
	public void onCreate() {
		super.onCreate();
		stop = false;
		cont = 0;
		l1 = (ListView) pruebaService.ACTIVIDAD.findViewById(R.id.ListView01);
		array1 = new ArrayList<String>();
		//Recupero la instancia del shareData
			singleton = ShareDataPruebas.getInstance();
		this.iniciarServicio();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		stop=true;
	}
	
	private void iniciarServicio(){
		
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop){
					cont++;
					
					array1.add(cont+"");
					
					dataHandler.sendEmptyMessage(0);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(cont == 3){
						try {
							lanzarActivity();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				

				
			}
		});
		t1.start();
	}
	
    private Handler dataHandler = new Handler() {
     	 @Override
     	 public void handleMessage(Message msg) {
     		ArrayAdapter<String> aa = new ArrayAdapter<String>(pruebaService.ACTIVIDAD.getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    array1);
            
           // ListView lv = (ListView)DTCsService.ACTIVIDAD.findViewById(R.id.LVDTCs);
            
            l1.setAdapter(aa);
     	 }
    };
	
    private void lanzarActivity() throws InterruptedException{
		Intent intent = new Intent(pruebaService.ACTIVIDAD, ActivityFromService.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getApplication().startActivity(intent);
		
		while(true){
			if(singleton.getCallEmergency().contains("YES")){
				pruebaService.ACTIVIDAD.finish();
			}else if(singleton.getCallEmergency().contains("NO")){
				break;
			}
		}
    }
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Coloque su código aquí
		return null;
	}
}
