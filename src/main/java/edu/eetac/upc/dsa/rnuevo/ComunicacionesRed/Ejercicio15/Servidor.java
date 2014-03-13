package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio15;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private static ServerSocket Servidor;
	private static int puerto = 5000;
	private static int conexiones = 0;
	
	public static List<ServidorThread> clientes = new ArrayList<ServidorThread>();

	public static void main(String[] args) {

		try {

			Servidor = new ServerSocket(puerto);
			Socket conexion=null;
			
			while (true) {
				conexion = Servidor.accept();
				ServidorThread nuevaConexion = new ServidorThread(conexion);
				nuevaConexion.start();
				clientes.add(nuevaConexion);
				conexiones++; 
			}

		} catch (Exception e) {}

	}

}

