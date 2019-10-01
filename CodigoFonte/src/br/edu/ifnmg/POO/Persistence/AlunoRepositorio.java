/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author petronio
 */
public class AlunoRepositorio {
    
    public BancoDados bd;
    
    public AlunoRepositorio(){
        bd = new BancoDados();
    }
    
    public boolean Salvar(Aluno obj){
        try {
            
            PreparedStatement sql = bd.getConexao()
                    .prepareStatement("insert into Alunos(nome, cpf) values(?,?)");
            
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));
            
            if(sql.executeUpdate() > 0) 
                return true;
            else
                return false;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return false;
        
    }
    
    public Aluno Abrir(int id){
        try {
            
             PreparedStatement sql = bd.getConexao()
                     .prepareStatement("select * from Alunos where id = ?");
             
             sql.setInt(1, id);
             
             ResultSet resultado = sql.executeQuery();
             
             
             resultado.next();
             
             Aluno aluno = new Aluno();
             
             aluno.setId( resultado.getInt("id"));
             aluno.setNome( resultado.getString("nome"));
             aluno.setCpf( resultado.getString("cpf"));
             
             return aluno;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
}
