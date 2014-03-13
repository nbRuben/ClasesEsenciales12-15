package edu.eetac.upc.dsa.rnuevo.ComunicacionesRed.Ejercicio12;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

	private static ServerSocket Servidor;
	private static int puerto = 5000;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
	private static String date = sdf.format(new Date());

	public static void main(String[] args) {

		try {
			Servidor = new ServerSocket(puerto);
			Socket conexion = Servidor.accept();

			salida = new DataOutputStream(conexion.getOutputStream());
			salida.flush();

			entrada = new DataInputStream(conexion.getInputStream());
			salida.writeUTF(date);

		} catch (Exception e) {
		}

	}

}
