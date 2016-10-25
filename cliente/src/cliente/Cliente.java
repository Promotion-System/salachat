package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
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

	public Cliente(String path,String name){
		try {
			Scanner scanner = new Scanner(new File(path));
			ip=scanner.nextLine();
			puerto=scanner.nextInt();
			scanner.close();
			this.name=name;
			String salaElegida="";
			cliente = new Socket(ip,puerto);
			do{
				String salasDisponibles=new DataInputStream(cliente.getInputStream()).readUTF();
				System.out.println(salasDisponibles+'\n');

				BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
				salaElegida = buffer.readLine();


				JsonObject json = new JsonObject();
				this.sala=salaElegida;
				json.addProperty("nombre", sala);
				new DataOutputStream(cliente.getOutputStream()).writeUTF(json.toString());

			}while(!salaElegida.equals("1")&&!salaElegida.equals("2")&&!salaElegida.equals("3")&&!salaElegida.equals("4"));
		
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

			String in = "C:\\Users\\Pablo\\Workspace\\salaChat\\chat.config";
			new Cliente(in,texto);
		} catch (Exception e) {
			System.err.println("Se cerro la conexión");
		}
	} 
}
