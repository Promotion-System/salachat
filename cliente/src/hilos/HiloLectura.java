package hilos;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloLectura extends Thread {
	
	Socket cliente;

	public HiloLectura(Socket cliente) {
		this.cliente=cliente;
	}
	
	public void run(){
		String texto="";
		
		try {
			while(true){
				
					texto=new DataInputStream(cliente.getInputStream()).readUTF();
					System.out.println(texto+'\n');
				
				
			}
		} catch (IOException e) {
			System.out.println("Cliente desconectado");
		}
	}

}
