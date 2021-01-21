/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.totvscoins;

import static com.mycompany.totvscoins.TotvsCoins.connectionUrl;
import static com.mycompany.totvscoins.TotvsCoins.estoque;
import static com.mycompany.totvscoins.TotvsCoins.integrante;
import static com.mycompany.totvscoins.TotvsCoins.movimento;
import static com.mycompany.totvscoins.TotvsCoins.produto;
import static com.mycompany.totvscoins.TotvsCoins.saldo_Integrante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author gisele.nuncherino
 */
@WebService(serviceName = "TotvsCoin")
@Stateless()
public class TotvsCoin {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
   String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DBCoins;user=gisele;password=1234";
    
   Produto produto = new Produto();
   Estoque estoque = new Estoque();
   Integrante integrante = new Integrante();
   Saldo_Integrante saldo_Integrante = new Saldo_Integrante();
   Movimentacao movimento = new Movimentacao();

    public Integrante getIntegrante(int cod_integrante) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexao = DriverManager.getConnection(connectionUrl);
            Statement stmt = conexao.createStatement();
            String SQL = "SELECT * FROM integrantes where D_E_L_E_T_ <> '*' and cod_integrante = " + cod_integrante;
    	    
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                integrante.setcod_integrante(rs.getInt("cod_integrante"));
                integrante.setdtNasc(((ResultSet) rs).getDate("dtNasc"));
                integrante.setemail(rs.getString("email"));
                integrante.setnome(rs.getString("nome"));

            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return integrante;

    }
    
    public Saldo_Integrante getSaldoIntegrante(int cod_integrante) {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    String SQL = "SELECT * FROM saldos_integrantes where D_E_L_E_T_ <> '*' and cod_integrante = " + cod_integrante;
    	    
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                saldo_Integrante.setcod_integrante(rs.getInt("cod_integrante"));
                saldo_Integrante.setsaldo(rs.getInt("saldo"));
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return saldo_Integrante;

    }
    
    public ArrayList<String> getListaProdutoGeral() {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    String SQL = "SELECT * FROM produtos where D_E_L_E_T_ <> '*' ";
    	    
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                produto.setcod_prod(rs.getInt("codigo"));
                produto.setdescricao(rs.getString("descricao"));
                produto.setval_coins(rs.getInt("val_coins"));
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return getListaProdutoGeral();

    }
    
    public ArrayList<String> setResgate(Integer cod_prod, Integer cod_integrante) {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    
    	    //busca o produto
    	    String SQL = "SELECT * FROM produtos where D_E_L_E_T_ <> '*' and cod_prod = " + cod_prod;
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                produto.setcod_prod(rs.getInt("cod_prod") );
                produto.setdescricao(rs.getString("descricao") );
                produto.setval_coins(rs.getInt("val_coins") );
            }
    	    
    	    //baixa do estque
    	    SQL = "SELECT * FROM estoques where D_E_L_E_T_ <> '*' and cod_prod = " + produto.getcod_prod();
    	    rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                estoque.setquantidade(rs.getInt("quantidade") - 1);
            }
            
    	    SQL = "UPDATE estoques set quantidade = " + estoque.getquantidade() + " where D_E_L_E_T_ <> '*' and cod_prod = " + produto.getcod_prod();
    	    rs = stmt.executeQuery(SQL);
    	    
    	    //baixa do saldo do integrante
    	    SQL = "SELECT * FROM saldos_integrantes where D_E_L_E_T_ <> '*' and cod_integrante = " + cod_integrante;
    	    rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                saldo_Integrante.setsaldo(rs.getInt("saldo") - produto.getval_coins());
            }
            
    	    SQL = "UPDATE saldos_integrantes set saldo = " + saldo_Integrante.getsaldo() + " where D_E_L_E_T_ <> '*' and cod_integrante = " + cod_integrante;
    	    rs = stmt.executeQuery(SQL);
    	    
    	    //grava movimentacao
    	    movimento.setcod_integrante(cod_integrante);
    	    movimento.setcod_prod(cod_prod);
    	    movimento.setquantidade(1);
    	    //movimento.setdtBaixa(Calendar.getInstance().getTime());
    	    movimento.setD_E_L_E_T_("");
    	    
       	    //SQL = "INSERT INTO movimentacoes ([cod_prod],[cod_integrante],[quantidade],[data_retirada],[D_E_L_E_T_]) values (" + movimento.getcod_prod() + "," + movimento.getcod_integrante() + "," + movimento.getquantidade() + "," + movimento.getdtBaixa() + "," + movimento.getD_E_L_E_T_() + ")";
            SQL = "INSERT INTO movimentacoes ([cod_prod],[cod_integrante],[quantidade],[D_E_L_E_T_]) values (" + movimento.getcod_prod() + "," + movimento.getcod_integrante() + "," + movimento.getquantidade() + "," + movimento.getD_E_L_E_T_() + ")";
       	    rs = stmt.executeQuery(SQL);
    	    
        } catch (Exception e) {
            e.getMessage();
        }
        return getListaProdutoGeral();
    }
}
