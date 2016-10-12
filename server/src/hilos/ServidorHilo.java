package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorHilo extends Thread{
	
	private Socket cliente;
	private ArrayList<Socket> lista;
	
	public ServidorHilo(Socket cliente, ArrayList<Socket> lista) {
		super();
		this.cliente=cliente;
		this.lista=lista;
	}
	
	public void run(){
		String texto="";
		
		try {
			texto = new DataInputStream(cliente.getInputStream()).readUTF();
			while(!texto.equals("Salir")){
				System.out.println(texto);
				for(Socket lector: lista){
					if(lector!=cliente){
						new DataOutputStream(lector.getOutputStream()).writeUTF(texto);
					}
				}
				texto = new DataInputStream(cliente.getInputStream()).readUTF();
			}
			System.out.println("El cliente se ha desconectado");
		} catch (IOException e) {
			System.out.println("El cliente se ha desconectado");
		}
	}

	
}
