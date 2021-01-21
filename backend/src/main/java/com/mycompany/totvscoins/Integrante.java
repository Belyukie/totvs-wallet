package com.mycompany.totvscoins;

import java.io.Serializable;
import java.sql.Date;

public class Integrante implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cod_integrante;
    private String nome;
    private String email;
    private Date dtNasc;
    private String D_E_L_E_T_;

    public Integrante() {
    }

    public Integrante(Integer cod_integrante) {
        this.cod_integrante = cod_integrante;
    }

    public Integrante(Integer cod_integrante, String nome, String D_E_L_E_T_, String email, Date dtNasc) {
        this.cod_integrante = cod_integrante;
        this.nome = nome;
        this.D_E_L_E_T_ = D_E_L_E_T_;
        this.email = email;
        this.dtNasc = dtNasc;
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
     * @return the nome
     */
    public String getnome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setnome(String nome) {
        this.nome = nome;
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
    public String getemail() {
        return email;
    }

    /**
     * @param val_coins the val_coins to set
     */
    public void setemail(String email) {
        this.email = email;
    }
    
    /**
     * @return the val_coins
     */
    public Date getdtNasc() {
        return dtNasc;
    }

    /**
     * @param val_coins the val_coins to set
     */
    public void setdtNasc(Date data) {
        this.dtNasc = data;
    }
}