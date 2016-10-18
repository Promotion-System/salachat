package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.JsonObject;

import hilos.HiloEscritura;
import hilos.HiloLectura;

public class Cliente {
	private Socket cliente;
	private String name;
	private String ip;
	private String sala;
	private int puerto;

	public Cliente(String path,String name,String sala){
		try {
			Scanner scanner = new Scanner(new File(path));
			ip=scanner.nextLine();
			puerto=scanner.nextInt();
			scanner.close();
			this.name=name;
			cliente = new Socket(ip,puerto);
			JsonObject json = new JsonObject();
			this.sala=sala;
			json.addProperty("nombre", sala);
			
		
			new DataOutputStream(cliente.getOutputStream()).writeUTF(json.toString());
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
			System.out.println("Ingrese sala: Disponibles 1,2,3,4");
			String sala = buffer.readLine();
			String in = "C:\\Users\\Pablo\\Workspace\\salaChat\\chat.config";
			new Cliente(in,texto, sala);
		} catch (Exception e) {
			System.err.println("Se cerro la conexión");
		}
	} 
}
