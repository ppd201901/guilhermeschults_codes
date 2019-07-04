package ppd.jantar.filosofos.mutex.JantarDosFilosofos;

public class Filosofos implements Runnable 
{
	
	  EstadoTalhares estadoTalher;
	  Talher talherEsquerdo, talherDireito;
	  int idFilosofo;
	  String nomeFilosofo;
	  
    public Filosofos(String nomeFilosofo, int idFilosofo, Talher talherEsquerdo, Talher talherDireito, EstadoTalhares estadoTalher) 
	 {
		  this.estadoTalher = estadoTalher;
		  this.talherEsquerdo = talherEsquerdo;
		  this.talherDireito = talherDireito;
		  this.idFilosofo = idFilosofo;
		  this.nomeFilosofo = nomeFilosofo;
	 }

	public static void main(String[] args)
	{
		
		Talher[] talher = new Talher[5];
		Filosofos[] filosofo = new Filosofos[5];
	    EstadoTalhares estadoTalher = new EstadoTalhares();
	    	    
	    for (int i = 0; i < 5; i++) 
	    {
	      talher[i] = new Talher();
	    }
	    
	    System.out.println("Cada coluna equivale a um Filosofo:\n");
	    System.out.println("Coluna 1 = Filosofo 0");
	    System.out.println("Coluna 2 = Filosofo 1");
	    System.out.println("Coluna 3 = Filosofo 2");
	    System.out.println("Coluna 4 = Filosofo 3");
	    System.out.println("Coluna 5 = Filosofo 4\n");
	    
	    System.out.println("X = filosofo esta comendo");
	    System.out.println("P = filosofo liberou o talher e agora esta pensando");
	    System.out.println("T = filosofo tentou pegar o garfo mas nao conseguiu "
	    		+ "pois verificou que o seu vizinho da esquerda ou direita esta comendo\n");

	    
	    
	    for (int i = 0; i < 5; i++)
	    {
	    	if(i == 0)
	    	{
	    	filosofo[i] = new Filosofos("Platao", i, talher[i], talher[(i + 4) % 5], estadoTalher);
	    	}
	    	else if(i == 1)
	    	{
	    	filosofo[i] = new Filosofos("Socrates", i, talher[i], talher[(i + 4) % 5], estadoTalher);
	    	}
	    	else if(i == 2)
	    	{
	    	filosofo[i] = new Filosofos("Aristoteles", i, talher[i], talher[(i + 4) % 5], estadoTalher);
	    	}
	    	else if(i == 3)
	    	{
	    	filosofo[i] = new Filosofos("Tales", i, talher[i], talher[(i + 4) % 5], estadoTalher);
	    	}
	    	else
	    	{
	    	filosofo[i] = new Filosofos("Sofocles", i, talher[i], talher[(i + 4) % 5], estadoTalher);
	    	}	      
	      new Thread(filosofo[i]).start();
	    }

	}

	public void run() 
	{	
		 while (true) 
		 {
		    estadoTalher.pegarGarfo(idFilosofo, talherEsquerdo, talherDireito);
		    comer();
		    estadoTalher.liberarGarfo(idFilosofo, talherEsquerdo, talherDireito);
		    pensar();
		 }		
	}
	
	private void comer()
	{
		 try 
		 {
		      Thread.sleep(3000);
		      System.out.println("Filosofo " + idFilosofo + " Comendo");
		   		      
		 } catch (Exception e) 
		 {
			 System.out.println("Erro:" + e.getMessage());
		 }
    }
	
	private void pensar()
	{
		 try 
		 {
		      Thread.sleep(3000);
		      System.out.println("Filosofo " + idFilosofo + " Pensando");
		      
		 } catch (Exception e)
		 {
			 System.out.println("Erro:" + e.getMessage());
		 }
    }

}
