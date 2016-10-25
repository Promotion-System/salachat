package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import hilos.HiloCreadorServidor;

public class Servidor {

	private int i = 0;
	private int puerto;
	private String ip;
	private int cantidadMaximaDeClientes;
	//ArrayList<Socket>lista = new ArrayList<>();
	HashMap<Sala, ArrayList<Socket>> salas=new HashMap<>();
	public Servidor(String path){
		ServerSocket servidor;
		try {
			Scanner scanner = new Scanner(new File(path));
			ip= scanner.nextLine();
			puerto=scanner.nextInt();
			cantidadMaximaDeClientes=scanner.nextInt();
			scanner.close();
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor en linea");
			while(i<cantidadMaximaDeClientes){
				Socket cliente  = servidor.accept();
							
				new HiloCreadorServidor(cliente,salas).start();;
				i++;
			}

			servidor.close();
			System.out.println("El Servidor se ha cerrado");
		} catch (IOException e) {
			System.err.println("Ocurrió un problema con el Servidor");
		}
	}
	
	public static void main(String[] args) {
		new Servidor("C:\\Users\\Pablo\\Workspace\\salaChat\\chat.config");
	}
}
