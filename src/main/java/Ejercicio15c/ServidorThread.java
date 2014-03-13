package Ejercicio15c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
public class ServidorThread extends Thread{
	
	private Socket cliente;
	public DataOutputStream salida;
	public DataInputStream entrada;
	private static int contador = 0;
	private String[] ordenes; 
	private Boolean acabar=false;
	public String jugadorActual;
	public static String primerJugador;
	public String segundoJugador;
	public static String jugador2;
	public static String jugador1;
	public String[] apuestas = new String[2];
	public static int numeroApuestas=0;
	//private static Vector misClientes = new Vector();
	public static int conexiones =0; 
	
	public static Socket[] clientes = new Socket[2];
	
	public ServidorThread(Socket cliente){
		clientes[conexiones]=cliente;
		try{
			salida = new DataOutputStream(clientes[conexiones].getOutputStream());
			
			entrada = new DataInputStream(clientes[conexiones].getInputStream());
		}catch(Exception e){}
		
		this.start();
	}
	
	
	public void run(){
		try{
			
			String opcion[];
			String respuesta = entrada.readUTF();
			opcion = respuesta.split(" ");
			System.out.println(ordenes[0]);
			while (respuesta != null){
				if(opcion[0].equals("PLAY")){
					if(conexiones ==0){
						jugador1=opcion[1];
						salida.writeUTF("WAIT");
						conexiones++;
					}else if (conexiones == 1){
						jugador2=opcion[1];
						salida.writeUTF("VERSUS " + jugador1);
						salida = new DataOutputStream(clientes[0].getOutputStream());
						salida.writeUTF("VERSUS " + jugador2);
					}
				}
			}

				
		}catch(Exception e){}
	}
}

