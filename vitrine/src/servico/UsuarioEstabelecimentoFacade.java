package servico;

import java.util.List;

import javax.ejb.Stateless;

import modelo.Estabelecimento;
import modelo.Usuario;
import modelo.UsuarioEstabelecimento;
import exception.AppException;


@Stateless
public class UsuarioEstabelecimentoFacade  extends GenericFacade<UsuarioEstabelecimento> {

	public UsuarioEstabelecimento recuperarEstabelecimento(Usuario usuario) 
			throws AppException {
		String query = "SELECT DISTINCT e FROM UsuarioEstabelecimento e"
				+ " WHERE e.usuario = :usuario";
		UsuarioEstabelecimento usuarioEstabelecimento = null;
		try {
			List<UsuarioEstabelecimento> entidades = getEntityManager()
					.createQuery(query, UsuarioEstabelecimento.class)
					.setParameter("usuario", usuario)
					.getResultList();
			if (entidades.size() > 0) {
				usuarioEstabelecimento = entidades.get(0);
			}
			return usuarioEstabelecimento;
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

}
