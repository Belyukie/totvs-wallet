package com.mycompany.totvscoins;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 * Data: 21/01/2021
 * @author gisele.nuncherino
 */
@WebService(serviceName = "TotvsCoin")
@Stateless()
public class TotvsCoin {

 
   String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DBCoins;user=gisele;password=1234";
    
   Produto produto = new Produto();
   Estoque estoque = new Estoque();
   Integrante integrante = new Integrante();
   Saldo_Integrante saldo_Integrante = new Saldo_Integrante();
   Movimentacao movimento = new Movimentacao();

   
   /* Retorna os dados da movimentacao do estoque */
   @WebMethod(operationName = "getMovimento")
   public Movimentacao getMovimento() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexao = DriverManager.getConnection(connectionUrl);
            Statement stmt = conexao.createStatement();
            String SQL = "SELECT * FROM movimentacoes ";
 
    	    ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                movimento.setcod_prod(rs.getInt("cod_prod"));
                movimento.setcod_integrante(rs.getInt("cod_integrante"));
                movimento.setquantidade(rs.getInt("quantidade"));
                movimento.setdtBaixa(rs.getDate("data_retirada"));
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return movimento;

    }
   
   
   /* Retorna os dados do integrante */
   @WebMethod(operationName = "getIntegrante")
   public Integrante getIntegrante(int cod_integrante) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexao = DriverManager.getConnection(connectionUrl);
            Statement stmt = conexao.createStatement();
            String SQL = "SELECT * FROM integrantes where cod_integrante = " + cod_integrante;
 
    	    ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                integrante.setcod_integrante(rs.getInt("cod_integrante"));
                //integrante.setdtNasc(((ResultSet) rs).getDate("dtNasc"));
                integrante.setemail(rs.getString("email"));
                integrante.setnome(rs.getString("nome"));

            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return integrante;

    }
    
    /* Retorna o saldo de Totvs Coin do integrande */
   @WebMethod(operationName = "getSaldoIntegrante")
    public Saldo_Integrante getSaldoIntegrante(int cod_integrante) {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    String SQL = "SELECT * FROM saldos_integrantes where cod_integrante = " + cod_integrante;
    	    
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
    
   /* Retorna o saldo em estoque do produto */
   @WebMethod(operationName = "getEstoque")
    public Integer getEstoque(int cod_produto) {
        Integer saldo = 0;
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    String SQL = "SELECT * FROM estoques where cod_prod = " + cod_produto;
    	    
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                saldo = rs.getInt("quantidade");
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return saldo;

    }
    
     /* Retorna a lista de produtos cadastrados */
    @WebMethod(operationName = "getListaProdutoGeral")
    public ArrayList<Produto> getListaProdutoGeral() {
        ArrayList<Produto> lstProdutos = new ArrayList<Produto>();
        
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    String SQL = "SELECT * FROM produtos ";
    	    
    	    ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                produto = new Produto();
                produto.setcod_prod(rs.getInt("cod_prod"));
                produto.setdescricao(rs.getString("descricao"));
                produto.setval_coins(rs.getInt("val_coins"));
                lstProdutos.add(produto);
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
        
        return lstProdutos;

    }
    
     /* Realiza o restate do produto:
        1) baixa do estoque do produto
        2) atualizacao do saldo de Totvs Coin do integrante
        3) grava a movimentacao do estoque
    */
    @WebMethod(operationName = "setResgate")
    public void setResgate(Integer cod_prod, Integer cod_integrante) {
         java.util.Date dtBaixa = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(dtBaixa.getTime());
        
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	    Connection conexao = DriverManager.getConnection(connectionUrl);
    	    Statement stmt = conexao.createStatement();
    	    
    	    //busca o produto
    	    String SQL = "SELECT * FROM produtos where cod_prod = " + cod_prod;
    	    ResultSet rs = stmt.executeQuery(SQL);
            Integer nRegs = 0;
            
            while (rs.next()) {
                produto.setcod_prod(rs.getInt("cod_prod") );
                produto.setdescricao(rs.getString("descricao") );
                produto.setval_coins(rs.getInt("val_coins") );
            }
    	    
    	    //baixa do estque
    	    SQL = "SELECT * FROM estoques where cod_prod = " + produto.getcod_prod();
    	    rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                estoque.setquantidade(rs.getInt("quantidade") - 1);
            }
            
    	    SQL = "UPDATE estoques set quantidade = " + estoque.getquantidade() + " where cod_prod = " + produto.getcod_prod();
    	    nRegs = stmt.executeUpdate(SQL);
    	    
    	    //baixa do saldo do integrante
    	    SQL = "SELECT * FROM saldos_integrantes where cod_integrante = " + cod_integrante;
    	    rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                saldo_Integrante.setsaldo(rs.getInt("saldo") - produto.getval_coins());
            }
            
    	    SQL = "UPDATE saldos_integrantes set saldo = " + saldo_Integrante.getsaldo() + " where cod_integrante = " + cod_integrante;
    	    nRegs = stmt.executeUpdate(SQL);
    	    
    	    //grava movimentacao
    	    movimento.setcod_integrante(cod_integrante);
    	    movimento.setcod_prod(cod_prod);
    	    movimento.setquantidade(1);
    	    
       	    SQL = "INSERT INTO movimentacoes ([cod_prod],[cod_integrante],[quantidade],[data_retirada],[D_E_L_E_T_]) values (" + movimento.getcod_prod() + "," + movimento.getcod_integrante() + "," + movimento.getquantidade() + "," + movimento.getdtBaixa() + "," + movimento.getD_E_L_E_T_() + ")";
            nRegs = stmt.executeUpdate(SQL);
    	    
        } catch (Exception e) {
            e.getMessage();
        }
        return;
    }
}
