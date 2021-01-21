package com.mycompany.totvscoins;
import java.io.Serializable;

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cod_prod;
    private String descricao;
    private String D_E_L_E_T_;
    private int val_coins;

    public Produto() {
    }

    public Produto(Integer cod_prod) {
        this.cod_prod = cod_prod;
    }

    public Produto(Integer cod_prod, String descricao, String D_E_L_E_T_, int val_coins) {
        this.cod_prod = cod_prod;
        this.descricao = descricao;
        this.D_E_L_E_T_ = D_E_L_E_T_;
        this.val_coins = val_coins;
    }

    /**
     * @return the cod_prod
     */
    public int getcod_prod() {
        return cod_prod;
    }

    /**
     * @param cod_prod the cod_prod to set
     */
    public void setcod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    /**
     * @return the descricao
     */
    public String getdescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setdescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the val_coins
     */
    public int getval_coins() {
        return val_coins;
    }

    /**
     * @param val_coins the val_coins to set
     */
    public void setval_coins(int val_coins) {
        this.val_coins = val_coins;
    }
}