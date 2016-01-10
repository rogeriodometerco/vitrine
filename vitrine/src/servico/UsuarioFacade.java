package servico;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import exception.AppPersistenceException;

import modelo.Usuario;

@Stateless
public class UsuarioFacade extends GenericFacade<Usuario> {

	public Usuario recuperarPeloEmail(String email) throws Exception {
		String sql = "SELECT x FROM Usuario x WHERE" +
				" x.email = :email";
		return getEntityManager()
				.createQuery(sql, Usuario.class)
				.setParameter("email", email)
				.getSingleResult();
	}

}
