/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import br.com.gerenciadorprojetos.entities.Profissional;
import java.util.StringTokenizer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 *
 * @author vanessa
 */
public class ProfissionalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsObject: value = "+value);
        if (value == null || (value.trim().length() == 0)) {
            return value;
        }
        Profissional profissional = new Profissional();
        
        int hyphenCount = 0;
        StringTokenizer hyphenTokenizer = new StringTokenizer(value, "-");
        while (hyphenTokenizer.hasMoreTokens()) {
            String token = hyphenTokenizer.nextToken();
            if (hyphenCount == 0) {
                profissional.setCpf(token);
            }
            if (hyphenCount == 1) {
                profissional.setNome(token);
            }
            if (hyphenCount == 2) {
                profissional.setFuncao(token);
            }
            hyphenCount++;
        }
        return profissional;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Profissional profissional = null;
        if (value instanceof Profissional) {
            profissional = (Profissional) value;
            StringBuilder profissionalString = new StringBuilder();
            profissionalString.append(profissional.getCpf() + "-");
            profissionalString.append(profissional.getNome() + "-");
            profissionalString.append(profissional.getFuncao());
            System.out.println("getAsString: return "+profissionalString.toString());
            return profissionalString.toString();
        }
        return "";
    }
}
