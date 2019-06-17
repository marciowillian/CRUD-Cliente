package com.mwcc.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mwcc.util.cdi.CDIServiceLocator;
import com.mwcc.util.dao.PerfilDAO;
import com.mwcc.util.model.Perfil;

@FacesConverter(value = "PerfilConverter")
public class PerfilConverter implements Converter{
	
	private PerfilDAO perfis;
	
	public PerfilConverter() {
		perfis = CDIServiceLocator.getBean(PerfilDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Perfil retorno = null;
		
		if(value != null || value != "") {
			Long id = new Long(value);
			
			retorno = perfis.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Perfil perfil = (Perfil)value;
			
			return perfil.getId() == null ? null : perfil.getId().toString();
		}
			
		return "";
	}

}
