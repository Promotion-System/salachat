package hilos;

import java.net.Socket;
import java.util.ArrayList;

public class HiloCreadorServidor extends Thread {
	private Socket cliente;
	private ArrayList<Socket> lista;
	
	public HiloCreadorServidor(Socket cliente, ArrayList<Socket> lista) {
		super();
		this.cliente = cliente;
		this.lista = lista;
	}
	
	public void run(){
		new ServidorHilo(cliente, lista).start();
	}
	

}
