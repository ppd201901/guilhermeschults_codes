package ppd.chat.socket.ChatSocket;

import java.io.InputStream;
import java.util.Scanner;

public class RecebeMensagemDoServidor implements Runnable
{
	
	private InputStream servidor;

	public RecebeMensagemDoServidor(InputStream servidor) 
	{
		this.servidor = servidor;
	}

	public void run() 
	{
		try(Scanner scanner = new Scanner(this.servidor))
		{
			while (scanner.hasNextLine()) 
			{
				System.out.println(scanner.nextLine());
			}
		}
	}

}
