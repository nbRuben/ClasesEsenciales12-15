package Ejercicio15c;

import java.net.*;
import java.io.*;

public class Ejercicio15_2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			Socket cliente = new Socket("127.0.0.1", 5000);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String nombreJugador;
			String opcion;
			System.out.println("Introduce tu nombre: ");
			nombreJugador = br.readLine();
			salida.writeUTF("PLAY " + nombreJugador);
			String respuesta=entrada.readUTF();
			String[] ordenes = respuesta.split(" ");
			while(respuesta!=null){
				
				if(ordenes[0].equals("WAIT")){
					System.out.println(respuesta);
				}else if(ordenes[0].equals("VERSUS")){
					System.out.println(respuesta);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
