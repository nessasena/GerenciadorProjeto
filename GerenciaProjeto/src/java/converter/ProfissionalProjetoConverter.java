/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author vanessa
 */
public class ProfissionalProjetoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || (value.trim().length() == 0)) {
            return value;
        }
        ProfissionalProjeto projeto = new ProfissionalProjeto();
        projeto.setId(Long.parseLong(value));
        
        return projeto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ProfissionalProjeto profissionalProjeto = null;
        if (value instanceof ProfissionalProjeto) {
            profissionalProjeto = (ProfissionalProjeto) value;
            StringBuilder projetoString = new StringBuilder();
            projetoString.append(profissionalProjeto.getId());

            System.out.println("getAsString: return "+projetoString.toString());
            return projetoString.toString();
        }
        return "";
    }
    
    
    
}
