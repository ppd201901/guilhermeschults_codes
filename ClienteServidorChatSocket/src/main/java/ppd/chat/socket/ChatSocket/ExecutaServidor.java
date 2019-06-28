package ppd.chat.socket.ChatSocket;

import java.io.IOException;

/**
*
* @author Guilherme Ferreira Schults
* 
* Classe main do servidor que sobe as portas do mesmo deixado-as online.
*/

public class ExecutaServidor {

	public static void main(String[] args) throws IOException 
	{
		new Servidor(1111).executa();
		new Servidor(2222).executa();
		new Servidor(3333).executa();
		new Servidor(4444).executa();

	}

}
