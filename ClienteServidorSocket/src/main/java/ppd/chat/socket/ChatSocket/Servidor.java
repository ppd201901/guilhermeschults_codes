package ppd.chat.socket.ChatSocket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Guilherme Ferreira Schults
* 
* 
* Classe servidor que inicializa as portas do servidos, deixando-as abertas
* aguardando conexao com algum cliente.
*/

public class Servidor 
{

		private int porta;
		private List<Socket> clientes;

		public Servidor(int porta)
		{
			this.porta = porta;
			this.clientes = new ArrayList<>();
		}

		public void executa() throws IOException 
		{
			try(ServerSocket servidor = new ServerSocket(this.porta))
			{
				System.out.println("Porta 1111 aberta!");
				System.out.println("Porta 2222 aberta!");
				System.out.println("Porta 3333 aberta!");
				System.out.println("Porta 4444 aberta!");

		
				while (true) 
				{
					Socket cliente = servidor.accept();
					System.out.println("Nova conexão com o cliente " + 
							cliente.getInetAddress().getHostAddress());
		
					this.clientes.add(cliente);
		
					VerificarTratarMensagensCliente tratarMensagensCliente = new VerificarTratarMensagensCliente(cliente, this);
					new Thread((Runnable) tratarMensagensCliente).start();
				}
			}
		}

		public void enviarMensagens(Socket clienteQueEnviouMensagem, String mensagem) 
		{
			for (Socket cliente : this.clientes)
			{
				if(!cliente.equals(clienteQueEnviouMensagem))
				{
					try 
					{
						PrintStream ps = new PrintStream(cliente.getOutputStream());
						ps.println(mensagem);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	

}
