package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

@SuppressWarnings("serial")
public class Autorizador implements PhaseListener {

	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		if("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return ;
		}
		
		//verificação
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			
			//Efetua renderização da tela
			context.renderResponse();
		}
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	

}
