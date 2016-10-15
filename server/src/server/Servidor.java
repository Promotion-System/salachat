package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import hilos.HiloCreadorServidor;

public class Servidor {

	int i = 0;
	//ArrayList<Socket>lista = new ArrayList<>();
	HashMap<Integer, ArrayList<Socket>> salas=new HashMap<>();
	public Servidor(int puerto){
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor en linea");
			while(i<100){
				Socket cliente  = servidor.accept();
				int sala = new DataInputStream(cliente.getInputStream()).readInt();
				if(salas.containsKey(sala)){
					salas.get(sala).add(cliente);
				}
				else{
					ArrayList<Socket> lista = new ArrayList<>();
					lista.add(cliente);
					salas.put(sala,lista);
				}
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
		new Servidor(10000);
	}
}
