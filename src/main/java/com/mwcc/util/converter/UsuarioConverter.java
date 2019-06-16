package com.mwcc.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mwcc.util.cdi.CDIServiceLocator;
import com.mwcc.util.dao.UsuarioDAO;
import com.mwcc.util.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioDAO usuarios;

	public UsuarioConverter() {
		usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if (value != null || value.trim().equals("")) {
			Long id = new Long(value);

			retorno = usuarios.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;

			return usuario.getId() == null ? null : usuario.getId().toString();
		}

		return null;
	}

}
