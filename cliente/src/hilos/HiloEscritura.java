package hilos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class HiloEscritura extends Thread {
	private Socket cliente;
	private String name;
	public HiloEscritura(Socket cliente,String name){
		this.cliente=cliente;
		this.name=name;
	}
	
	public void run(){
		try {
			InputStreamReader leer = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(leer);
			String texto = buffer.readLine();
			while(!texto.equals("Salir")) {
				new DataOutputStream(cliente.getOutputStream()).writeUTF(name + ": " + texto);
				texto = buffer.readLine();
			}
			cliente.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
