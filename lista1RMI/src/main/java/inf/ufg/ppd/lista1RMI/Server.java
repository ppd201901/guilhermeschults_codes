package inf.ufg.ppd.lista1RMI;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Guilherme Ferreira Schults
 */
public class Server extends Constantes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Server() throws MalformedURLException, RemoteException
	
	{
		
		 LocateRegistry.createRegistry(RMI_PORT);
		 String localServico = RMI_ID_LOCALHOST;   
		 RemoteProcedures objRemoteServer = new RemoteProceduresImpl();
		try
		{
			
			Naming.rebind(localServico, objRemoteServer);
			System.out.println("Servidor esta ativo.");
			
		}catch(RemoteException re)
		{
            System.out.println("Erro Remoto: "+re.toString());
            
        }catch(Exception e)
		{
            System.out.println("Erro Local: "+e.toString());
        }
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException  
    { 
       
		new Server();
    } 	
	
}
