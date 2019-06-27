package ppd.chat.socket.ChatSocket;

import java.io.IOException;
import java.rmi.UnknownHostException;

public class ExecutaCliente {

	public static void main(String[] args)throws UnknownHostException,	IOException
	{
		new Cliente("127.0.0.1", 1111).executa();
		new Cliente("127.0.0.1", 2222).executa();
		new Cliente("127.0.0.1", 3333).executa();
		new Cliente("127.0.0.1", 4444).executa();

	}

}
