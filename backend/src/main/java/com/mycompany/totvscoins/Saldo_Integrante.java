package com.mycompany.totvscoins;
import java.io.Serializable;

public class Saldo_Integrante implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cod_integrante;
    private Integer saldo;
    private String D_E_L_E_T_;

    public Saldo_Integrante() {
    }

    public Saldo_Integrante(Integer cod_integrante) {
        this.cod_integrante = cod_integrante;
    }

    public Saldo_Integrante(Integer cod_integrante, String D_E_L_E_T_, Integer saldo) {
        this.cod_integrante = cod_integrante;
        this.saldo = saldo;
        this.D_E_L_E_T_ = D_E_L_E_T_;

    }

    /**
     * @return the cod_integrante
     */
    public int getcod_integrante() {
        return cod_integrante;
    }

    /**
     * @param cod_integrante the cod_integrante to set
     */
    public void setcod_integrante(int cod_integrante) {
        this.cod_integrante = cod_integrante;
    }

    /**
     * @return the D_E_L_E_T_de
     */
    public String getD_E_L_E_T_() {
        return D_E_L_E_T_;
    }

    /**
     * @param D_E_L_E_T_de the D_E_L_E_T_de to set
     */
    public void setD_E_L_E_T_(String D_E_L_E_T_) {
        this.D_E_L_E_T_ = D_E_L_E_T_;
    }

    /**
     * @return the cod_integrante
     */
    public int getsaldo() {
        return saldo;
    }

    /**
     * @param cod_integrante the cod_integrante to set
     */
    public void setsaldo(int saldo) {
        this.saldo = saldo;
    }

}