package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.Sala;

public class HiloCreadorServidor extends Thread {
	private Socket cliente;
	HashMap<Sala, ArrayList<Socket>> salas;
	
	public HiloCreadorServidor(Socket cliente,HashMap<Sala, ArrayList<Socket>> salas) throws IOException {
		super();
		Sala sala;
		String salasDisponibles ="Ingrese sala Disponible: 1,2,3,4";
		
		do{

			new DataOutputStream(cliente.getOutputStream()).writeUTF(salasDisponibles);
				
			String nombreSala = new DataInputStream(cliente.getInputStream()).readUTF();
		
			JsonParser parser = new JsonParser();
			
			JsonElement elemento = parser.parse(nombreSala);
			JsonObject json = elemento.getAsJsonObject();
			sala = new Gson().fromJson(json,Sala.class);
			
		}while(!sala.getNombre().equals("1")&&!sala.getNombre().equals("2")&&!sala.getNombre().equals("3")&&!sala.getNombre().equals("4"));
		
		if(salas.containsKey(sala)){
			salas.get(sala).add(cliente);
		}
		else{
			ArrayList<Socket> lista = new ArrayList<>();
			lista.add(cliente);
			salas.put(sala,lista);
		}
		
		this.cliente = cliente;
		this.salas = salas;
	}
	
	public void run(){
		new ServidorHilo(cliente, salas).start();
	}
	

}
