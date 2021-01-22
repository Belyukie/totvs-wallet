package com.mycompany.totvscoins;
import java.sql.SQLException;
import java.util.ArrayList;


public class WebServicePublisher{
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        java.util.Date dtBaixa = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(dtBaixa.getTime());
         
        Integrante user = new Integrante();
        Saldo_Integrante saldoFunc = new Saldo_Integrante();
        Movimentacao movimento = new Movimentacao();
        
        //instanciando o WS
        TotvsCoin wsTC = new TotvsCoin();
        
        //dados do funcionario
        user = wsTC.getIntegrante(1);
        saldoFunc = wsTC.getSaldoIntegrante(user.getcod_integrante());
        
        System.out.println("** Dados do funcionário *** ");
        System.out.println("Matricula............: " + user.getcod_integrante());
        System.out.println("Nome.................: " + user.getnome());
        System.out.println("Email................: " + user.getemail());
        System.out.println("Totvs Coin (saldo)...: " + String.valueOf(saldoFunc.getsaldo()));
        
        System.out.println("");
        System.out.println("");
        
        // lista dos produtos
        ArrayList<Produto> lstProdutos = new ArrayList<Produto>();
        lstProdutos = wsTC.getListaProdutoGeral();
        System.out.println("*** Produtos/Recompensas Disponiveis ***");
        for(Produto p : lstProdutos)
        {
            System.out.println("Código: " + String.valueOf(p.getcod_prod()) + " - Prêmio:" +  String.format("%-25.25s", p.getdescricao()) + " - Valor em Totvs Coin:" + String.format("%-5.5s", p.getval_coins()) + "  - Disponível: " + wsTC.getEstoque(p.getcod_prod()) + " unidades ");
        }
        
        //Solicitando um resgate do funcionario cod 1 - produto cod 3 - dayoff
        System.out.println("");
        System.out.println("");
        System.out.println("*** Solicitando um resgate de Totvs Coin do funcionario matricula: 1 (Gisele Nuncherino) - produto: 3 (Day-off) ***");      
        System.out.println("");
        wsTC.setResgate(3, 1);
        System.out.println("*** Quantidade disponivel do produto atualizado..: " + wsTC.getEstoque(3) + " unidades " );
        System.out.println("*** Saldo disponivel do funcionário atualizado...: " + wsTC.getSaldoIntegrante(1).getsaldo() + " Totvs Coin" );
        System.out.println("");
        
        System.out.println("** Histórico de resgate salvo na base de dados ***");
        
        movimento = wsTC.getMovimento();
        movimento.setdtBaixa(sqlDate);
        System.out.println("Quantidade retirada: " + movimento.getquantidade() + " Data da retirada: " + movimento.getdtBaixa());
        
        System.out.println("");
        
    }
 
}