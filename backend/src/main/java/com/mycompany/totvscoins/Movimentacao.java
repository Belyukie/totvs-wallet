package com.mycompany.totvscoins;
import java.io.Serializable;

public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cod_integrante;
    private Integer cod_prod;
    private Integer quantidade;
    //private Date dtBaixa;
    private String D_E_L_E_T_;

    public Movimentacao() {
    }

    public Movimentacao(Integer cod_integrante) {
        this.cod_integrante = cod_integrante;
    }

    public Movimentacao(Integer cod_integrante, String D_E_L_E_T_, Integer quantidade, /*Date dtBaixa,*/ Integer cod_prod) {
        this.cod_integrante = cod_integrante;
        this.cod_prod = cod_prod;
        this.quantidade = quantidade;
       // this.dtBaixa = dtBaixa;
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
     * @return the cod_prod
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

    /**
     * @return the cod_integrante
     */
   /* public Date getdtBaixa() {
        return dtBaixa;
    }
*/
    /**
     * @param cod_integrante the cod_integrante to set
     */
    /*
    public void setdtBaixa(Date date) {
        this.dtBaixa = date;
    }
*/
    
}