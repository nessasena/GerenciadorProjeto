/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import br.com.gerenciadorprojetos.entities.Projeto;
import java.util.StringTokenizer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author vanessa
 */
public class ProjetoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        if (value == null || (value.trim().length() == 0)) {
            return value;
        }
        Projeto projeto = new Projeto();
        
        int hyphenCount = 0;
        StringTokenizer hyphenTokenizer = new StringTokenizer(value, "-");
        while (hyphenTokenizer.hasMoreTokens()) {
            String token = hyphenTokenizer.nextToken();
            if (hyphenCount == 0) {
                projeto.setCodigo(Integer.parseInt(token));
            }
            if (hyphenCount == 1) {
                projeto.setNome(token);
            }            
            hyphenCount++;
        }
        return projeto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Projeto projeto = null;
        if (value instanceof Projeto) {
            projeto = (Projeto) value;
            StringBuilder projetoString = new StringBuilder();
            projetoString.append(projeto.getCodigo() + "-");
            projetoString.append(projeto.getNome());            
            System.out.println("getAsString: return "+projetoString.toString());
            return projetoString.toString();
        }
        return "";
    }
    
    
    
}
