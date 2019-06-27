package inf.ufg.ppd.lista1RMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;


/**
*
* @author Guilherme Ferreira Schults
*/
public class RemoteProceduresImpl extends UnicastRemoteObject  implements RemoteProcedures, Serializable{

	private static final long serialVersionUID = 1L;

	protected RemoteProceduresImpl() throws RemoteException {
		super();	
	}

	public JSONObject calcularReajusteJson(String nome, String cargo, double salario) throws JSONException  {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("R$ #,##0.00");		

		JSONObject json = new JSONObject();
	
		String mensagem = "";
		double porcentagemOperador, porcentagemProgramador;

		if (nome != null && cargo != null & salario > 0) {
			if (cargo.equalsIgnoreCase("operador")) {
				porcentagemOperador = salario * 0.20;
				salario += porcentagemOperador;
				mensagem = String.valueOf(df.format(salario));
			} else if (cargo.equalsIgnoreCase("programador")) {
				porcentagemProgramador = salario * 0.20;
				salario += porcentagemProgramador;
				mensagem = String.valueOf(df.format(salario));
			}
			try {
				json.put("Salario", mensagem);
				json.put("Nome", nome);	
				json.put("Cargo", cargo);
			} catch (JSONException e) {
				e.printStackTrace();
			}
				return json;
		}		
		return json;	
	}
	
	
	public JSONObject AprovacaoAlunoJson(double n1, double n2, double n3) throws RemoteException {
		double resultadoMedia = (n1 + n2) / 2;
		double resultadoMedia2 = resultadoMedia + n3 / 2;
		String aprovadoReprovado = "";
		String resultado = "";
		
		JSONObject json = new JSONObject();
	
		if (resultadoMedia >= 7.0) {
			aprovadoReprovado = String.valueOf(resultadoMedia);
			resultado = "O aluno esta aprovado. Nota: " + aprovadoReprovado + "";
		} else if (resultadoMedia > 3.0 && resultadoMedia < 7.0) {
			aprovadoReprovado = String.valueOf(resultadoMedia);
			resultado = "O aluno devera realizar a N3. Nota: " + aprovadoReprovado + "";
		} else if (resultadoMedia2 >= 5.0) {
			aprovadoReprovado = String.valueOf(resultadoMedia2);
			resultado = "Aprovado. Nota: " + aprovadoReprovado + "";
		}
		try {
			json.put("Situacao", resultado);
			//json.toString();
			//jsonArray.put(json).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


	public JSONObject verificaMaioridadeJson(String sexo, int idade) throws RemoteException, JSONException {
		JSONObject json = new JSONObject();
		String mensagem="";
		
		if(sexo.equalsIgnoreCase("M") && idade >= 18) {
			mensagem="Usuario informado e maior de idade";
		} else if(sexo.equalsIgnoreCase("M") && idade < 18) {
			mensagem="Usuario informado nao e maior de idade";
		} else if(sexo.equalsIgnoreCase("F")  && idade >= 21) {
			mensagem="Usuario informado e maior de idade";
		} else if(sexo.equalsIgnoreCase("F")  && idade < 21) {
			mensagem="Usuario informado nao e maior de idade";
		} else {
			mensagem="Dados incorretos";
		}
		try {
			json.put("Situacao", mensagem);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


	public JSONObject calculaPesoIdealJson(String altura, String sexo) throws RemoteException, JSONException {
		JSONObject json = new JSONObject();
		String resultado="";
		
		double altura1;
		
		DecimalFormat df = new DecimalFormat();
        df.applyPattern("#.##");
		
		if(sexo.equalsIgnoreCase("masculino")) {
			altura1 = (72.7 * Double.parseDouble(altura)) - 58;
			resultado = "Seu peso ideal e: "+df.format(altura1)+" kg." ;
		} else if(sexo.equalsIgnoreCase("feminino")) {
			altura1 = (62.2 * Double.parseDouble(altura)) - 44.7;
			resultado = "Seu peso ideal e: "+df.format(altura1)+" kg.";
		} else {
			resultado="Dados incorretos";
		}
		try {
			json.put("Situacao", resultado);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


	public JSONObject verificaCategoriaNadadorJson(int idade) throws RemoteException, JSONException {
		JSONObject json = new JSONObject();
		String categoria="";
		
		if(idade >=5 && idade <= 7) {
			categoria = "Infantil A";
		} else if (idade >=8 && idade <= 10) {
			categoria = "Infantil B";
		} else if (idade >=11 && idade <= 13) {
			categoria = "Juvenil A";
		} else if (idade >=14 && idade <= 17) {
			categoria = "Juvenil B";
		} else {
			if(idade > 18) {
				categoria = "Adulto";
			}
		}
		
		try {
			json.put("Categoria", categoria);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
	}

	
	public JSONObject calculaSalarioLiquidoJson(String nome, String nivel, double salario, int dependentes)
			throws RemoteException, JSONException {
		
		JSONObject json = new JSONObject();
		String resultado="";
		double salarioLiquido=0.0;
		double calculaDesconto=0.0;
		
		DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");
	
		if(nivel.equalsIgnoreCase("A") && dependentes == 0) {
			calculaDesconto = (salario * 0.03);
			salarioLiquido = salario - calculaDesconto;
			//resultado =  " Funcionario:"+nome+ "N�vel: "+nivel+ " Novo Sal�rio: "+String.valueOf(salarioLiquido)+" ";
			resultado = String.valueOf(salarioLiquido);
		} else if (nivel.equalsIgnoreCase("A") && dependentes > 0) {
			calculaDesconto = (salario * 0.08);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("B") && dependentes == 0) {
			calculaDesconto = (salario * 0.05);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("B") && dependentes > 0) {
			calculaDesconto = (salario * 0.10);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("C") && dependentes == 0) {
			calculaDesconto = (salario * 0.08);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("C") && dependentes > 0) {
			calculaDesconto = (salario * 0.15);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("D") && dependentes == 0) {
			calculaDesconto = (salario * 0.10);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else if(nivel.equalsIgnoreCase("D") && dependentes > 0) {
			calculaDesconto = (salario * 0.17);
			salarioLiquido = salario - calculaDesconto;
			resultado = String.valueOf(salarioLiquido);
		} else {
			resultado =  " Por favor insira os dados e tente novamente.";
		}
		//resultado =  "Funcionario:"+nome+ "\nN�vel: "+nivel+ "\nNovo Sal�rio: "+df.format(salarioLiquido)+" ";
		try {
			json.put("Funcionario", nome);
			json.put("Nivel", nivel);
			json.put("Novo salario", df.format(salarioLiquido));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
		
	}

	
	public JSONObject calculaCreditoJson(double saldoAnterior) throws RemoteException, JSONException {
		double credito=0.0;
		String resultado="";
		JSONObject json = new JSONObject();
		
		DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");
		
		
		if(saldoAnterior > 0 && saldoAnterior <= 200.0) {
			resultado="Seu saldo: "+saldoAnterior+", nao concede credito";
		} else if(saldoAnterior >= 201 && saldoAnterior <= 400) {
			credito = saldoAnterior * 0.2 + saldoAnterior;
			resultado="Saldo atual do cliente: "+saldoAnterior+". Percentual de credito especial disponivel: 20%. Novo saldo + credito:"+df.format(credito)+" ";
		} else if(saldoAnterior > 401 && saldoAnterior <= 601) {
			credito = saldoAnterior * 0.3 + saldoAnterior;
			resultado="Saldo atual do cliente: "+saldoAnterior+". Percentual de credito especial disponivel: 30%. Novo saldo + credito:"+df.format(credito)+"";
		} else if (saldoAnterior > 601){
			credito = (saldoAnterior * 0.4) + saldoAnterior;
			resultado="Saldo atual do cliente: "+saldoAnterior+". Percentual de credito especial disponivel: 40%. Novo saldo + credito:"+df.format(credito)+"";
		} else {
			resultado="Verifique os dados inseridos e tente novamente";
		}
		try {
			json.put("Credito Disponivel", resultado);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return json;	
	}

	
	public JSONObject calculaAposentadoriaJson(int idade, int tempoServico, String sexo) throws RemoteException, JSONException {
		JSONObject json = new JSONObject();
		String resultado="";
		
		if(sexo.equalsIgnoreCase("masculino")) {
			if(idade >= 65 && tempoServico >= 30) {
				resultado = " Trabalhador com "+idade+ " anos e tempo de Servico "+tempoServico+ ". Resultado: apto para aposentadoria";
			} else {
				resultado = " Trabalhador com "+idade+ " anos e tempo de Servico "+tempoServico+ ". Resultado: inapto para aposentadoria";
			}
		} else {
		
			if(sexo.equalsIgnoreCase("feminino")) {
				if(idade >= 60 && tempoServico >= 25) {
					resultado = "Trabalhadora com "+idade+ " anos e tempo de servico "+tempoServico+ ". Resultado: apta para aposentadoria";
				} else {
					resultado = "Trabalhadora com "+idade+ " anos e tempo de servico "+tempoServico+ ". Resultado: inapta para aposentadoria";
				}
			}
		}
		try {
			json.put("Situacao Aposentadoria", resultado);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return json;
	}
	
}
