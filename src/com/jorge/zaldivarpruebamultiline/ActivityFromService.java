package com.jorge.zaldivarpruebamultiline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityFromService extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	
	ShareDataPruebas singleton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityfromservice);
		
		singleton = ShareDataPruebas.getInstance();
		
		Button b1 = (Button) findViewById(R.id.Ok);
		Button b2 = (Button) findViewById(R.id.cancel);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				envioSMS();
				try {
					sendMail();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setSingleton("YES");
				finish();
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setSingleton("NO");
				finish();
				
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		setSingleton("NO");
	}
	
	public void setSingleton(String res){
		singleton.setCallEmergency(res);
	}
	
	private void envioSMS()
	{
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage("+34647871926", null, "El androide libre", null, null);
	}
	
	private void sendMail() throws Exception{
		GMailSender m= new GMailSender(); 
		 
	      String[] toArr = {"koke.zaldivar@gmail.com", "kokedjy2k@hotmail.com"}; 
	      m.setTo(toArr); 
	      m.setFrom("koke.zaldivar@gmail.com"); 
	      m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device."); 
	      m.setBody("Email body."); 
	      
	      try { 
	    	  
	    	 // FileOutputStream fOut = openFileOutput("prueba1",
               //       MODE_WORLD_READABLE);
//	    	  FileWriter f = new FileWriter("/sdcard/prueba/filelocation");
	    	  
//	    	  File f = new File(getFilesDir(),"prueba2");
//	    	  boolean creado = f.createNewFile();
	    	  
	    	  
	    	  
	          File root = Environment.getExternalStorageDirectory();
              File gpxfile = new File(root, "prueba3");
              FileWriter writer = new FileWriter(gpxfile);
              writer.flush();
              writer.close();

	    	  
	          m.addAttachment(gpxfile.getPath()); 
	   
	          if(m.send()) { 
	            Toast.makeText(this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
	          } else { 
	            Toast.makeText(this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
	          } 
	        } catch(Exception e) { 
	          //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
	          Log.e("MailApp", "Could not send email", e); 
	        } 
	}
}
