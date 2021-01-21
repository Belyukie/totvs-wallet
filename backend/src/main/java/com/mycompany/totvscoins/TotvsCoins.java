package com.mycompany.totvscoins;

//import javax.servlet.*;
//import javax.ws.rs.*;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService()
@SOAPBinding(style = Style.RPC)
public interface TotvsCoins {
    
   String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DBCoins;user=gisele;password=1234";
    
   Produto produto = new Produto();
   Estoque estoque = new Estoque();
   Integrante integrante = new Integrante();
   Saldo_Integrante saldo_Integrante = new Saldo_Integrante();
   Movimentacao movimento = new Movimentacao();

   @WebMethod Integrante getIntegrante(int cod_integrante);
   
   @WebMethod Saldo_Integrante getSaldoIntegrante(int cod_integrante);
   
   @WebMethod ArrayList<String> getListaProdutoGeral();
     
   @WebMethod ArrayList<String> setResgate(Integer cod_prod, Integer cod_integrante);
    
 }