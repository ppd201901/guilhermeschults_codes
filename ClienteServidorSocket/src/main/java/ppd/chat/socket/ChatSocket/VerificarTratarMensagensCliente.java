package ppd.chat.socket.ChatSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
*
* @author Guilherme Ferreira Schults
* 
* 
* Classe que trata as mensagens dos clientes enviadas por socket ao servidor.
*/

public class VerificarTratarMensagensCliente implements Runnable
{
	
	private Socket cliente;
	private Servidor servidor;

	public VerificarTratarMensagensCliente(Socket cliente, Servidor servidor)
	{
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() 
	{
		try(Scanner scanner = new Scanner(this.cliente.getInputStream())) 
		{
			while (scanner.hasNextLine()) 
			{
				servidor.enviarMensagens(this.cliente, scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}

}
