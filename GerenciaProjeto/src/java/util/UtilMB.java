/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author alunoruy
 */
@ManagedBean(name="utilMB")
public class UtilMB {

    public List<SelectItem> getFuncao() {
        List<SelectItem> funcao = new ArrayList<SelectItem>();

        for (FuncaoEnum s : FuncaoEnum.values()) {
            funcao.add(new SelectItem(s.toString(), s.toString()));
        }
        return funcao;
    }
    
    public List<SelectItem> getGrauInstrucao() {
        List<SelectItem> grauInstrucao = new ArrayList<SelectItem>();

        for (GrauInstrucaoEnum s : GrauInstrucaoEnum.values()) {
            grauInstrucao.add(new SelectItem(s.toString(), s.toString()));
        }
        return grauInstrucao;
    }
}
