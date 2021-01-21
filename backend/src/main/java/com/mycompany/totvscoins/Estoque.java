package com.mycompany.totvscoins;

import java.io.Serializable;

public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cod_prod;
    private Integer quantidade;
    private String D_E_L_E_T_;

    public Estoque() {
    }

    public Estoque(Integer cod_prod) {
        this.cod_prod = cod_prod;
    }

    public Estoque(Integer cod_prod, String D_E_L_E_T_, Integer quantidade) {
        this.cod_prod = cod_prod;
        this.quantidade = quantidade;
        this.D_E_L_E_T_ = D_E_L_E_T_;

    }

    /**
     * @return the cod_integrante
     */
    public int getcod_prod() {
        return cod_prod;
    }

    /**
     * @param cod_integrante the cod_integrante to set
     */
    public void setcod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
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
    public int getquantidade() {
        return quantidade;
    }

    /**
     * @param cod_integrante the cod_integrante to set
     */
    public void setquantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}