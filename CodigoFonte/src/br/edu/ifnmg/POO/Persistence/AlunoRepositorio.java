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
import java.util.ArrayList;
import java.util.List;

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
            
            if(obj.getId() == 0){
                PreparedStatement sql = bd.getConexao()
                        .prepareStatement("insert into Alunos(nome, cpf) values(?,?)");

                sql.setString(1, obj.getNome());
                sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));

                if(sql.executeUpdate() > 0){ 
                    return true;
                }
                else
                    return false;
            } else {
                PreparedStatement sql = bd.getConexao()
                        .prepareStatement("update Alunos set nome = ?, cpf = ? where id = ?");

                sql.setString(1, obj.getNome());
                sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));
                sql.setInt(3, obj.getId());

                if(sql.executeUpdate() > 0) 
                    return true;
                else
                    return false;
            }
            
            
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
    
    public boolean Apagar(Aluno obj){
        try {
            PreparedStatement sql = bd.getConexao()
                    .prepareStatement("delete from Alunos where id = ?");
            
            sql.setInt(1, obj.getId());
            
            if(sql.executeUpdate() > 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public List<Aluno> Buscar(Aluno filtro){
        try {
            
            String where = "";
            
            if(filtro.getNome() != null && !filtro.getNome().isEmpty())
                where += "nome like '%"+filtro.getNome() + "%'";
            
            if(filtro.getCpf() != null && !filtro.getCpf().isEmpty()){
                if(where.length() > 0)
                    where += " and ";
                where += "cpf = '"+filtro.getCpf().replace(".", "").replace("-", "") + "'";
            }
            
            String consulta = "select * from Alunos";
            
            if(where.length() >0 )
                consulta += " where " + where;
            
             PreparedStatement sql = bd.getConexao()
                     .prepareStatement(consulta);
             
             ResultSet resultado = sql.executeQuery();
             
             List<Aluno> alunos = new ArrayList<>();
             
             while(resultado.next()) {
             
                Aluno aluno = new Aluno();
             
                aluno.setId( resultado.getInt("id"));
                aluno.setNome( resultado.getString("nome"));
                aluno.setCpf( resultado.getString("cpf"));
                
                alunos.add(aluno);
             }
             return alunos;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
}
