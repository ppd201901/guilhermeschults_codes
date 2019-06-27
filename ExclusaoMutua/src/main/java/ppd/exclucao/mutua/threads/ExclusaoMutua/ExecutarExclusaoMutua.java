package ppd.exclucao.mutua.threads.ExclusaoMutua;

public class ExecutarExclusaoMutua {
	
	static RegiaoCritica bloqueado = null;
    static int contadorThreads = 0;

	public static void main(String[] args) throws InterruptedException 
	{

	        Thread[] threads=new Thread[4];

	        threads[0]=new Thread(new Threads());
	        threads[1]=new Thread(new Threads());
	        threads[2]=new Thread(new Threads());
	        threads[3]=new Thread(new Threads());

	        bloqueado = new RegiaoCritica();
	        
	        System.out.println("Inicio de execucao das Threads:\n");

	        threads[0].start();
	        threads[1].start();
	        threads[2].start();
	        threads[3].start();
	        threads[0].join();
	        threads[1].join();
	        threads[2].join();
	        threads[3].join();

	        System.out.println("\nTotal de Threads que acessaram a regiao critica:");
	        System.out.println(contadorThreads);
	    

	}

}
