package ppd.exclucao.mutua.threads.ExclusaoMutua;

public class Threads implements Runnable
{

	@Override
	public void run() 
	{	
		    int i = 1;
		    
	        while(i <= 5)
	        {
	            ExecutarExclusaoMutua.bloqueado.bloquearRegiaoCritica();
	            ExecutarExclusaoMutua.contadorThreads++;
	            ExecutarExclusaoMutua.bloqueado.desbloquearRegiaoCritica();
	            
	            i++;
	        }	
	}

}
