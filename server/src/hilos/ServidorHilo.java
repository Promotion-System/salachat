package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import server.Sala;


public class ServidorHilo extends Thread{
	
	private Socket cliente;
	HashMap<Sala, ArrayList<Socket>> salas;
	
	public ServidorHilo(Socket cliente,HashMap<Sala, ArrayList<Socket>> salas) {
		super();
		this.cliente=cliente;
		this.salas=salas;
	}
	
	public void run(){
		String texto="";
		
		try {
			texto = new DataInputStream(cliente.getInputStream()).readUTF();
			while(!texto.equals("Salir")){
				System.out.println(texto);
				Iterator<Sala> iterador = salas.keySet().iterator();
				while(iterador.hasNext()){
					Sala key=iterador.next();
					if(salas.get(key).contains(cliente)){
						for(Socket lector: salas.get(key)){
							if(lector!=cliente){
								new DataOutputStream(lector.getOutputStream()).writeUTF(texto);
							}
						}
						texto = new DataInputStream(cliente.getInputStream()).readUTF();
						
					}
				}
			}
			System.out.println("El cliente se ha desconectado");
		} catch (IOException e) {
			System.out.println("El cliente se ha desconectado");
		}
	}

	
}
