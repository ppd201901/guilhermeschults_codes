package inf.ufg.ppd.lista1RMI;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.Scanner;

import org.json.JSONException;
/**
*
* @author Guilherme Ferreira Schults
*/
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	private static Scanner dados;

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, JSONException, UnmarshalException {
		
		dados = new Scanner(System.in);	
		RemoteProcedures procedures;
		String nome,
		       profissao,
			   sexo,
			   altura,
			   nivel;
		double n1,
			   n2,
			   n3,
			   salario,
			   saldoAnterior;
		int idade,
			dependentes,
			tempoServico,
			opcao = 0;
		
		do {
		menu();
		opcao = dados.nextInt();
		switch (opcao) {
		case 1:
			questao1();
			dados.nextLine();
			System.out.println("Informe a nota N1 do aluno");
			n1 = Double.parseDouble(dados.nextLine());
			System.out.println("Informe a nota N2 do aluno");
			n2 = Double.parseDouble(dados.nextLine());
			System.out.println("Informe a nota N3 do aluno");
			n3 = Double.parseDouble(dados.nextLine());
		
			try 
			{
				procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
				System.out.println("Servidor responde: \nResultado: " + procedures.AprovacaoAlunoJson(n1, n2, n3));
				
			} catch (MalformedURLException e) {
				System.out.println("Erro:" + e.toString());
				e.printStackTrace();
			} catch (RemoteException e) {
				System.out.println("Erro:" + e.toString());
				e.printStackTrace();
			} catch (NotBoundException e) {
				System.out.println("Erro:" + e.toString());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Entrada Inv�lida:" + e.toString());
				System.exit(-1);
			}
			break;
		case 2:
			questao2();
			dados.nextLine();
			System.out.println("Informe o nome:");
			nome = dados.nextLine();
			System.out.println("Informe o cargo:");
			profissao = dados.nextLine();
			System.out.println("informe o salario:");
			salario = Double.parseDouble(dados.nextLine());
			
			switch(profissao) {
			
			case "operador":
				procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
				System.out.println("Servidor responde: \nResultado: " + procedures.calcularReajusteJson(nome, profissao, salario));
				break;
			case "programador":
				procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
				System.out.println("Servidor responde: \nResultado: " + procedures.calcularReajusteJson(nome, profissao, salario));
				break;
			}
			break;
		case 3:
			questao3();
			dados.nextLine();
			System.out.println("Informe o sexo - (M) ou (F):");
			sexo = dados.nextLine();
			System.out.println("Informe a idade:");
			idade = dados.nextInt();
			
			procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
			System.out.println("Servidor responde: \nResultado: " + procedures.verificaMaioridadeJson(sexo, idade));
			break;
		
		case 4:
		questao4();
		dados.nextLine();
		System.out.println("Informe a altura");
		altura = dados.nextLine();
		System.out.println("Informe o sexo - (Masculino) ou (Feminino):");
		sexo = dados.nextLine();
		
		procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
		System.out.println("Servidor responde: \nResultado: " + procedures.calculaPesoIdealJson(altura, sexo));
		
		break;
		
		case 5:
		questao5();
		
		System.out.println("Informe a idade:");
		idade = dados.nextInt();
			
		procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
		System.out.println("Servidor responde: \nResultado: " + procedures.verificaCategoriaNadadorJson(idade));
			
		break;
		
		case 6:
			questao6();
			dados.nextLine();
			System.out.println("Informe o nome:");
			nome = dados.nextLine();
			System.out.println("Informe nivel - (A), (B), (C) ou (D) :");
			nivel = dados.nextLine();
			System.out.println("Informe o salario:");
			salario = dados.nextDouble();
			System.out.println("Informe a quantidade de dependentes:");
			dependentes = dados.nextInt();
			
			procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
			System.out.println("Servidor responde: \nResultado: " + procedures.calculaSalarioLiquidoJson(nome, nivel, salario, dependentes));

		break;
		
		case 7:
			questao7();
			dados.nextLine();
			
			System.out.println("Informe o saldo conta:");
			saldoAnterior = dados.nextDouble();
			
			procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
			System.out.println("Servidor responde: \nResultado: " + procedures.calculaCreditoJson(saldoAnterior));
		break;
		
		case 8:
			questao8();
					
			System.out.println("Informe a idade:");
			idade = dados.nextInt();
			System.out.println("Informe o tempo de servico:");
			tempoServico = dados.nextInt();
			dados.nextLine();
			System.out.println("Informe o sexo - (Masculino) ou (Feminino):");
			sexo = dados.nextLine();
			
			procedures = (RemoteProcedures) Naming.lookup(Constantes.RMI_ID_LOCALHOST);
			System.out.println("Servidor responde: \nResultado: " + procedures.calculaAposentadoriaJson(idade, tempoServico, sexo));
			
		break;
		
		default:
            System.out.println("Programa encerrado.");
			}
		
		}while(opcao != 0);
	}
	
	
	  public static void menu(){    
	        System.out.println("\nSistemas Distribuidos - INF UFG - Lista 1 usando RMI\n");
            System.out.println(" Questao 1 - Calcular Aprovacao de Aluno");
            System.out.println(" Questao 2 - Calcular Reajuste de Salario de Funcionario");
            System.out.println(" Questao 3 - Calcular Maioridade");
            System.out.println(" Questao 4 - Calcular Peso Ideal");
            System.out.println(" Questao 5 - Calcular Categoria Nadador");
            System.out.println(" Questao 6 - Calcular Salario Liquido Funcionario");
            System.out.println(" Questao 7 - Calcular Credito Disponivel para cliente");
            System.out.println(" Questao 8 - Calcular Aposentadoria");
            System.out.println(" Digite 0 para sair do programa.\n");
	    }
	  
	    public static void questao1(){
	        System.out.println("Voce esta na questao 1.\n");
	    }	    
	    public static void questao2(){
	        System.out.println("Voce esta na questao 2.\n");
	    }    
	    public static void questao3(){
	        System.out.println("Voce esta na questao 3.\n");
	    }   
	    public static void questao4(){
	        System.out.println("Voce esta na questao 4.\n");
	    }
	    public static void questao5(){
	        System.out.println("Voce esta na questao 5.\n");
	    }
	    public static void questao6(){
	        System.out.println("Voce esta na questao 6.\n");
	    }
	    public static void questao7(){
	        System.out.println("Voce esta na questao 7.\n");
	    }
	    public static void questao8(){
	        System.out.println("Voce esta na questao 8.\n");
	    }

}

