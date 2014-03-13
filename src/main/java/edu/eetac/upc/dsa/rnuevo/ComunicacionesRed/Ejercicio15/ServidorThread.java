package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
public class ServidorThread extends Thread{
	
	private Socket cliente;
	public DataOutputStream salida;
	private DataInputStream entrada;
	private static int contador = 0;
	private String[] ordenes; 
	private Boolean acabar=false;
	public String jugadorActual;
	public static String jugador2;
	public static String jugador1;
	public String[] apuestas = new String[2];
	public static int numeroApuestas=0;
	
	public ServidorThread(Socket cliente){
		this.cliente=cliente;
	}
	
	public void enviarMensaje(String mensaje){
		try{
			salida.writeUTF(mensaje);
		}catch(Exception e){}
	}
	
	public void run(){
		try{
			salida = new DataOutputStream(this.cliente.getOutputStream());
			salida.flush();
			
			entrada = new DataInputStream(this.cliente.getInputStream());
			
			String opcion;

			while (acabar == false){

				opcion=entrada.readUTF();

				ordenes = new String[3];
				ordenes=opcion.split(" ");
				if(ordenes[0].equals("PLAY")){
					contador ++;
					if(contador==1){
						jugadorActual = ordenes[1];
						jugador1=ordenes[1];
						salida.writeUTF("WAIT");
					}else if(contador==2){
						jugador2=ordenes[1];
						for(int i=0; i<Servidor.clientes.size(); i++){
							if(i==0){
								Servidor.clientes.get(i).salida.writeUTF("VERSUS " +  jugador1 + " " +jugador1);
							}else if(i==1) {
								Servidor.clientes.get(i).salida.writeUTF("VERSUS " +  jugador2 + " " +jugador1);
							}
						}
						jugadorActual=ordenes[1];
					}
				}else if (ordenes[0].equals("MY") && ordenes[1].equals("BET")){
					numeroApuestas++;
					for(int i=0; i<Servidor.clientes.size(); i++){
						Servidor.clientes.get(i).salida.writeUTF("BET OF " + this.jugadorActual + " " +ordenes[2]);
						if(i==0){
							Servidor.clientes.get(0).salida.writeUTF("WAIT");

							Servidor.clientes.get(0).apuestas[0] = ordenes[2];
							Servidor.clientes.get(0).apuestas[1] = ordenes[3];
						}else if(i==1){
							Servidor.clientes.get(1).salida.writeUTF("YOUR BET");
							Servidor.clientes.get(1).apuestas[0] = ordenes[2];
							Servidor.clientes.get(1).apuestas[1] = ordenes[3];
						}
						
					}
					if(numeroApuestas==2){
						int suma = Integer.valueOf(Servidor.clientes.get(0).apuestas[0]) +Integer.valueOf(Servidor.clientes.get(1).apuestas[0]);
						String ganador="";
						for(int i =0; i< Servidor.clientes.size();i++){
							if(suma==Integer.valueOf(Servidor.clientes.get(i).apuestas[1])){
								ganador=("WINNER " + Servidor.clientes.get(i).jugadorActual);
							}
						}
						for(int i=0; i<Servidor.clientes.size();i++){
							if(ganador.equals("")==false){
									Servidor.clientes.get(i).salida.writeUTF(ganador);
								}else{
									Servidor.clientes.get(i).salida.writeUTF("WINNER NONE");
								}
						}
					}
				}
			}
		}catch(Exception e){}
	}
}

