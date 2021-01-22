package com.mycompany.totvscoins;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class WebServicePublisher{
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        String USER_NAME = "totvswallet";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "totvs@123"; // GMail password
        String RECIPIENT = "giselen@gmail.com";
        
        Produto prod = new Produto();
        
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
            prod = p;
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
        
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "[Totvs Wallet] Resgate de Totvs Coins";
        String body = "Olá, " + user.getnome() + "\n" ;
        body += "Estamos confirmando seu resgate de: 1 " +  prod.getdescricao() + " no valor de " + prod.getval_coins() + " Totvs Coins!" + "\n" ;
        body += "Alinhe com sua liderança e aproveite seu prêmio." + "\n" ;
        body += "Conte sempre com a Totvs Wallet em suas conquistas!!! " + "\n" ;

        sendFromGMail(from, pass, to, subject, body);
        
    }

        private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}