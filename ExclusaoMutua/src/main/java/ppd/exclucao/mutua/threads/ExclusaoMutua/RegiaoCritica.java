package ppd.exclucao.mutua.threads.ExclusaoMutua;


import java.util.concurrent.atomic.AtomicBoolean;

public class RegiaoCritica 
{
	
	private AtomicBoolean regiaoCriticaBloqueado = new AtomicBoolean(false);
    private volatile String threadAtual;

    public void bloquearRegiaoCritica() 
    {
        for (;;) 
        {
            if(!regiaoCriticaBloqueado.get())
            {
                if (regiaoCriticaBloqueado.compareAndSet(false, true)) 
                {
                    threadAtual = Thread.currentThread().getName();
                    System.out.println(threadAtual + " bloqueado");
                    return;
                }
            }
        }
    }


    public void desbloquearRegiaoCritica() 
    {
        System.out.println(threadAtual + " desbloqueado");
        regiaoCriticaBloqueado.set(false);
    }

}
