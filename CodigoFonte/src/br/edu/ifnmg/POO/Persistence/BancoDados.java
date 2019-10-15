/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class BancoDados {
    
    private Connection conexao;
    
    public BancoDados(){
        try {
            // Carrega o Driver do MySQL na memória
            Class.forName("com.mysql.jdbc.Driver");
            
            // Conecta ao banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/POO","root","pclsdw");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não foi encontrado!");
        } catch (SQLException ex) {
            System.out.println("Os dados da conexão com o banco de dados estão errados!");
            System.out.println(ex.getMessage());
            
        }
    }

    public Connection getConexao() {
        return conexao;
    }
    
    
}
