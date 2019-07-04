package ppd.jantar.filosofos.mutex.JantarDosFilosofos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EstadoTalhares {
	
	  Lock exclucaoMutua = new ReentrantLock();
	  Condition[] condicao = new Condition[5];
	  String[] estadoAtual = new String[5];
	  int[] id = new int[5];
	  
	  public void setEstadoTalher(int id, String s)
	  {
	    estadoAtual[id] = s;
	  }

	  public EstadoTalhares()
	  {
	    for (int i = 0; i < 5; i++) 
	    {
	      id[i] = i;
	      estadoAtual[i] = "P";
	      condicao[i] = exclucaoMutua.newCondition();
	    }
	  }

	  public void pegarGarfo(int id, Talher talherEsquerdo, Talher talherDireito)
	  {
	    exclucaoMutua.lock();
	    
	    try 
	    {
	      setEstadoTalher(id, "T");
	      while (!talherEsquerdo.getDisponibilidadeTalher() || !talherDireito.getDisponibilidadeTalher()) 
	      {
	        condicao[id].await();
	      }
	      talherEsquerdo.setDisponibilidadeTalher(false);
	      talherDireito.setDisponibilidadeTalher(false);
	      setEstadoTalher(id, "X");
	      imprimirResultadoEstadoTalhares(id);
	      
	    } catch (Exception e) 
	    {
	      e.printStackTrace();
	      
	    } finally 
	    {
	      exclucaoMutua.unlock();
	    }
	  }
	  
	  public void liberarGarfo(int id, Talher talherEsquerdo, Talher talherDireito) 
	  {
		    exclucaoMutua.lock();
		    
		    setEstadoTalher(id, "P");
		    
		    talherEsquerdo.setDisponibilidadeTalher(true);		    
		    talherDireito.setDisponibilidadeTalher(true);
		    
		    condicao[(id + 1) % 5].signalAll();		   
		    condicao[(id + 4) % 5].signalAll();
		    
		    imprimirResultadoEstadoTalhares(id);
		    
		    exclucaoMutua.unlock();
	   }
	  
	  void imprimirResultadoEstadoTalhares(int id) 
	  {
	    StringBuffer linha = new StringBuffer();
	    	    
	    for (int i = 0; i < 5; i++)
	    {
	    	linha.append(estadoAtual[i] + " ");
	    }
	    
	    System.out.println(linha + "(" + (id + 1) + ")");
	  }

}
