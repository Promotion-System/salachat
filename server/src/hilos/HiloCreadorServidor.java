package hilos;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class HiloCreadorServidor extends Thread {
	private Socket cliente;
	HashMap<Integer, ArrayList<Socket>> salas;
	
	public HiloCreadorServidor(Socket cliente,HashMap<Integer, ArrayList<Socket>> salas) {
		super();
		this.cliente = cliente;
		this.salas = salas;
	}
	
	public void run(){
		new ServidorHilo(cliente, salas).start();
	}
	

}
