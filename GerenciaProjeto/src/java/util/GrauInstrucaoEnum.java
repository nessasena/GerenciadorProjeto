/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alunoruy
 */
public enum GrauInstrucaoEnum {

    MEDIO {
        public String toString() {
            return "Ensino Medio";
        }
    },
    TECNICO{
        public String toString() {
            return "Nivel Tecnico";
        }
    },
    SUPERIOR{
        public String toString() {
            return "Ensino Superior";
        }
    },
    MESTRADO
    {
        public String toString() {
            return "Mestrado";
        }
    },
    DOUTORADO{
        public String toString() {
            return "Doutorado";
        }
    };
}
