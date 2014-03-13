package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio15;

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
			System.out.println("Introduce tu nombre: ");
			nombreJugador = br.readLine();
			salida.writeUTF("PLAY " + nombreJugador);
			String respuesta;
			while(true){
				respuesta=entrada.readUTF();
				String[] ordenes = respuesta.split(" ");
				if(ordenes[0].equals("WAIT")){
					System.out.println(respuesta);
				}else if(ordenes[0].equals("VERSUS")){
					System.out.println(ordenes[0] + " " + ordenes[1]);
					System.out.println("Turno del jugador: " +ordenes[2]);
					if(ordenes[2].equals(nombreJugador)){
						System.out.println("Introduce tu apuesta:");
						String apuesta = br.readLine();
						salida.writeUTF(apuesta);
					}
				}else if(ordenes[0].equals("BET")){
					System.out.println(respuesta);
					respuesta=entrada.readUTF();
					System.out.println(respuesta);
					if(respuesta.equals("YOUR BET")){
						System.out.println("Introduce tu apuesta:");
						String apuesta = br.readLine();
						salida.writeUTF(apuesta);
					}
				}else if(ordenes[0].equals("WINNER")){
					System.out.println("WINNER: "+ ordenes[1]);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
