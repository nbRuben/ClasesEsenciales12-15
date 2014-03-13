package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio12;

import java.net.*;
import java.io.*;

public class Ejercicio12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Socket cliente = new Socket("127.0.0.1", 5000);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			
			String hora = entrada.readUTF();
			System.out.println(hora);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
