package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio13;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidoresC extends Thread {
	
	private Socket cliente;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
	private static String date = sdf.format(new Date());
	
	public ServidoresC(Socket cliente){
		this.cliente=cliente;
	}
	
	public void run(){
		try{

			salida = new DataOutputStream(cliente.getOutputStream());
			salida.flush();

			entrada = new DataInputStream(cliente.getInputStream());
			salida.writeUTF(date);
		}catch(Exception e){}
	}
}
