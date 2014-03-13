package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio14;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidoresC14 extends Thread {
	
	private Socket cliente;
	private DataOutputStream salida;
	private DataInputStream entrada;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("E,d MMM, HH:mm:ss");
	private String date; 
	public ServidoresC14(Socket cliente){
		this.cliente=cliente;
	}
	
	public void run(){
		try{
		
			salida = new DataOutputStream(cliente.getOutputStream());
			salida.flush();

			entrada = new DataInputStream(cliente.getInputStream());
			
			
			int opcion = Integer.valueOf(entrada.readUTF());
			
			
			
			switch(opcion){
			
			case 0 : 	date= sdf.format(new Date());
						salida.writeUTF(date);
						break;
			case 1 :	
						date = sdf2.format(new Date());
						salida.writeUTF(date);	
						break;
			}
		
			
		}catch(Exception e){}
	}
}
