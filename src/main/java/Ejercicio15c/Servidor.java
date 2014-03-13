package Ejercicio15c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private static ServerSocket Servidor;
	private static int puerto = 5000;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	public static int conexiones = 0;
	
	public static List<ServidorThread> clientes = new ArrayList<ServidorThread>();

	public static void main(String[] args) {

		try {

			Servidor = new ServerSocket(puerto);
			Socket conexion=null;
			
			while (true) {
				conexion = Servidor.accept();
				ServidorThread nuevaConexion = new ServidorThread(conexion);
				//nuevaConexion.start();
				System.out.println("Nueva conexion: " +nuevaConexion.getName());
				clientes.add(nuevaConexion);
				conexiones++; 
			}

		} catch (Exception e) {}

	}
}