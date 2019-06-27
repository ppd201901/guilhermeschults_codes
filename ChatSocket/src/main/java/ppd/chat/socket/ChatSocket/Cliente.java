package ppd.chat.socket.ChatSocket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
*
* @author Guilherme Ferreira Schults
* 
* Classe que passa o endereco do cliente e sua porta, por socket, para conexao com servidor.
*/

public class Cliente 
{

	private String host;
	private int porta;

	public Cliente(String url, int porta) 
	{
		this.host = url;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException
	{
		try(Socket cliente = new Socket(this.host, this.porta); 
				Scanner teclado = new Scanner(System.in); 
				PrintStream saida = new PrintStream(cliente.getOutputStream())) 
		{
			System.out.println("Ok, esta tudo certo. O cliente se conectou ao servidor!");
	
			RecebeMensagemDoServidor mensagemRecebidaServidor = new RecebeMensagemDoServidor(cliente.getInputStream());
			new Thread(mensagemRecebidaServidor).start();
	
			while (teclado.hasNextLine())
			{
				saida.println(teclado.nextLine());
			}
			saida.close();
	        teclado.close();
	        cliente.close();  
		}
	}
}