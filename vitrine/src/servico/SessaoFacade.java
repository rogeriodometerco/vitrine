package servico;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import modelo.Estabelecimento;
import modelo.Usuario;
import modelo.UsuarioEstabelecimento;


// TODO Classe em desenvolvimento. Objetivo é manter um objeto de onde possam ser extraídas e compartilhadas
// informações da sessão do usuário.

@Stateful
public class SessaoFacade {

	private HttpSession httpSession;
	
	@PostConstruct
	public void construiu() {
		System.out.println("construiu SessaoFacade");
	}

	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private UsuarioEstabelecimentoFacade usuarioEstabelecimentoFacade;
	
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}
	
	
	public void login(HttpServletRequest httpServletRequest, String email, String senha) throws Exception {
		try {
			this.httpSession = httpServletRequest.getSession();
			httpServletRequest.login(email, senha);
			Usuario usuario = usuarioFacade.recuperarPeloEmail(email);
			System.out.println("usuario logado: " + usuario.getId() + " - " + usuario.getNome());
			Estabelecimento estabelecimento = null;
			UsuarioEstabelecimento usuarioEstabelecimento = usuarioEstabelecimentoFacade
					.recuperarEstabelecimento(usuario);
			if (usuarioEstabelecimento != null) {
				estabelecimento = usuarioEstabelecimento.getEstabelecimento();
			}
			System.out.println("estabelecimento logado: " + estabelecimento);
			httpSession.setAttribute("usuario", usuario);
			httpSession.setAttribute("estabelecimento", estabelecimento);
		} catch (ServletException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Usuario getUsuario() {
		return (Usuario)httpSession.getAttribute("usuario");
	}
	
	public Estabelecimento getEstabelecimento() {
		return (Estabelecimento)httpSession.getAttribute("estabelecimento");
	}
	
}
