package servico;

import java.util.List;

import javax.ejb.Stateless;

import modelo.Estabelecimento;
import modelo.Item;
import exception.AppException;

@Stateless
public class ItemFacade  extends GenericFacade<Item> {

	/**
	 * Lista os itens para o gerente do estabelecimento gerenciar os itens anunciados
	 * @param estabelecimento
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws AppException
	 */
	// TODO Obter referência para o estabelecimento sem necessidade de receber como parâmetro no método.
	public List<Item> listarParaGerenciar(Estabelecimento estabelecimento)//, int firstResult, int maxResults) 
			throws AppException {
		String query = "SELECT DISTINCT i FROM Item i"
				+ " WHERE i.estabelecimento = :estabelecimento"
				+ " ORDER BY i.id DESC";
		try {
			if (estabelecimento == null) {
				throw new AppException("É necessário um estabelecimento para listar os itens a gerenciar");
			}
			return getEntityManager().createQuery(query, Item.class)
					.setParameter("estabelecimento", estabelecimento)
					//.setFirstResult(firstResult)
					//.setMaxResults(maxResults)
					.getResultList();
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

	/**
	 * Lista os itens para o cliente.
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws AppException
	 */
	public List<Item> listarParaCliente() throws AppException {
		String query = "SELECT DISTINCT i FROM Item i"
				+ " ORDER BY i.id DESC";
		try {
			return getEntityManager().createQuery(query, Item.class)
					//.setFirstResult(firstResult)
					//.setMaxResults(maxResults)
					.getResultList();
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

}
