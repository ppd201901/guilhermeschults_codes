package inf.ufg.ppd.lista1RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.json.JSONException;
/**
*
* @author Guilherme Ferreira Schults
*/
public interface RemoteProcedures extends Remote {
		/**
		 * Procedure que calcula o reajuste de salario de um funcionario
		 * @param nome, cargo, salario
		 * @return object
		 */
		public Object calcularReajusteJson(String nome, String cargo, double salario) throws RemoteException, JSONException;
		/**
		 * Procedure que calcula se um aluno foi aprovado ou nao
		 * @param n1, n2, n3
		 * @return object
		 */
		public Object AprovacaoAlunoJson(double n1, double n2, double n3) throws RemoteException, JSONException;
		/**
		 * Procedure que verifica se um usuario e maior de idade 
		 * @param sexo, idade
		 * @return object
		 */
		public Object verificaMaioridadeJson(String sexo, int idade) throws RemoteException, JSONException;
		/**
		 * Procedure que calcula o peso ideal de uma pessoa
		 * @param altura, sexo
		 * @return object
		 */
		public Object calculaPesoIdealJson (String altura, String sexo) throws RemoteException, JSONException; 
		/**
		 * Procedure que verifica a categoria de um nadador
		 * @param idade
		 * @return object
		 */
		public Object verificaCategoriaNadadorJson (int idade) throws RemoteException, JSONException; 
		/**
		 * Procedure que calcula o salario liquido de um funcionario com base em seus dependentes
		 * @param nome, cargo, salario
		 * @return object
		 */
		public Object calculaSalarioLiquidoJson(String nome, String nivel, double salario, int dependentes) throws RemoteException, JSONException; 
		/**
		 * Procedure que calcula o percentual de credito disponivel para um cliente
		 * @param saldoAnterior
		 * @return object
		 */
		public Object calculaCreditoJson(double saldoAnterior) throws RemoteException, JSONException; 
		/**
		 * Procedure que calcula a aposentadoria
		 * @param idade, tempoServico, sexo
		 * @return object
		 */
		public Object calculaAposentadoriaJson(int idade, int tempoServico, String sexo) throws RemoteException, JSONException; 

}

