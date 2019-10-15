/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.DomainModel.Sexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petronio
 */
public class AlunoRepositorio extends BancoDados {
    
    public AlunoRepositorio(){
        super();
    }
    
    public boolean Salvar(Aluno obj){
        try {
            
            if(obj.getId() == 0){
                PreparedStatement sql = this.getConexao()
                        .prepareStatement("insert into Alunos(nome, cpf,sexo) values(?,?,?)",
                                Statement.RETURN_GENERATED_KEYS);

                sql.setString(1, obj.getNome());
                sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));
                sql.setString(3, obj.getSexo().name());

                if(sql.executeUpdate() > 0){ 
                    ResultSet chave = sql.getGeneratedKeys();
                    chave.next();
                    obj.setId(chave.getInt(1));
                    return true;
                }
                else
                    return false;
            } else {
                PreparedStatement sql = this.getConexao()
                        .prepareStatement("update Alunos set nome = ?, cpf = ?, sexo = ? where id = ?");

                sql.setString(1, obj.getNome());
                sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));
                sql.setString(3, obj.getSexo().name());
                sql.setInt(4, obj.getId());

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
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement("select * from Alunos where id = ?");
             
             sql.setInt(1, id);
             
             ResultSet resultado = sql.executeQuery();
             
             
             resultado.next();
             
             Aluno aluno = new Aluno();
             
             aluno.setId( resultado.getInt("id"));
             aluno.setNome( resultado.getString("nome"));
             aluno.setCpf( resultado.getString("cpf"));
             aluno.setSexo( Sexo.valueOf(resultado.getString("sexo")));
             
             return aluno;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    public boolean Apagar(Aluno obj){
        try {
            PreparedStatement sql = this.getConexao()
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
            
            if(filtro.getSexo() != null ){
                if(where.length() > 0)
                    where += " and ";
                where += "sexo = '"+filtro.getSexo().name() +"'";
            }
            
            String consulta = "select * from Alunos";
            
            if(where.length() >0 )
                consulta += " where " + where;
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement(consulta);
             
             ResultSet resultado = sql.executeQuery();
             
             List<Aluno> alunos = new ArrayList<>();
             
             while(resultado.next()) {
             
                Aluno aluno = new Aluno();
             
                aluno.setId( resultado.getInt("id"));
                aluno.setNome( resultado.getString("nome"));
                aluno.setCpf( resultado.getString("cpf"));
                aluno.setSexo( Sexo.valueOf(resultado.getString("sexo")));
                
                alunos.add(aluno);
             }
             return alunos;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
}
