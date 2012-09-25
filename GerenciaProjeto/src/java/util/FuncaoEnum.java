/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alunoruy
 */
public enum FuncaoEnum {
   ANALISTA_JUNIOR{
        public String toString() {
            return "Analista Junior";
        }
    },
   ANALISTA_PLENO{
        public String toString() {
            return "Analista Pleno";
        }
    },
   ANALISTA_SENIOR{
        public String toString() {
            return "Analista Senior";
        }
    },
   ARQUITETO{
        public String toString() {
            return "Arquiteto";
        }
    },
   PROGRAMADOR{
        public String toString() {
            return "Programador";
        }
    },
   GERENTE{
        public String toString() {
            return "Gerente";
        }
    };
}
