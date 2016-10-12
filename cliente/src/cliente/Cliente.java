package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import hilos.HiloEscritura;
import hilos.HiloLectura;

public class Cliente {
	private Socket cliente;
	private String name;

	public Cliente(String ip, int puerto,String name){
		try {
			this.name=name;
			cliente = new Socket(ip,puerto);
			new HiloLectura(cliente).start();
			new HiloEscritura(cliente,name).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		try {
			System.out.println("Ingrese nombre: ");
			InputStreamReader leer = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(leer);
			String texto = buffer.readLine();
			new Cliente("localhost", 10000,texto);
		} catch (Exception e) {
			System.err.println("Se cerro la conexi�n");
		}
	} 
}
