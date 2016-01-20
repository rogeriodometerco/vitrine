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
	public List<Item> listarPorEstabelecimento(Estabelecimento estabelecimento)//, int firstResult, int maxResults) 
			throws AppException {
		String query = "SELECT DISTINCT i FROM Item i"
				+ " WHERE i.estabelecimento = :estabelecimento"
				+ " ORDER BY i.id DESC";
		try {
			if (estabelecimento == null) {
				throw new AppException("É necessário um estabelecimento para listar os itens");
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

}
