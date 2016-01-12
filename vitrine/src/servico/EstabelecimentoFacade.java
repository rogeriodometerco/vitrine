package servico;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import modelo.Estabelecimento;
import modelo.Usuario;
import modelo.UsuarioEstabelecimento;
import exception.AppPersistenceException;


@Stateless
public class EstabelecimentoFacade  extends GenericFacade<Estabelecimento> {

	@EJB
	private UsuarioEstabelecimentoFacade usuarioEstabelecimentoFacade;
	
	public Estabelecimento criar(Usuario usuario, Estabelecimento entidade) throws Exception {
		Estabelecimento estabelecimentoCriado = salvar(entidade);

		// Cria relação do usuário com estabelecimento.
		UsuarioEstabelecimento usuarioEstabelecimento = new UsuarioEstabelecimento();
		usuarioEstabelecimento.setUsuario(usuario);
		usuarioEstabelecimento.setEstabelecimento(estabelecimentoCriado);
		usuarioEstabelecimentoFacade.salvar(usuarioEstabelecimento);
		//
		return estabelecimentoCriado;
	}

}
