/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import br.edu.ifnmg.POO.DomainModel.Professor;
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
public class ProfessorRepositorio extends BancoDados {

    public ProfessorRepositorio() {
        super();
    }
    
    
      
    public boolean Salvar(Professor obj){
        try {
            
            if(obj.getId() == 0){
                PreparedStatement sql = this.getConexao()
                        .prepareStatement("insert into Professores(nome, cpf) values(?,?)",
                                Statement.RETURN_GENERATED_KEYS);

                sql.setString(1, obj.getNome());
                sql.setString(2, obj.getCpf().replace(".", "").replace("-", ""));

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
                        .prepareStatement("update Professores set nome = ?, cpf = ? where id = ?");

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
    
    public Professor Abrir(int id){
        try {
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement("select * from Professores where id = ?");
             
             sql.setInt(1, id);
             
             ResultSet resultado = sql.executeQuery();
             
             
             resultado.next();
             
             Professor p = new Professor();
             
             p.setId( resultado.getInt("id"));
             p.setNome( resultado.getString("nome"));
             p.setCpf( resultado.getString("cpf"));
             
             return p;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    public boolean Apagar(Professor obj){
        try {
            PreparedStatement sql = this.getConexao()
                    .prepareStatement("delete from Professores where id = ?");
            
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
    
    public List<Professor> Buscar(Professor filtro){
        try {
            
            String where = "";
            
            if(filtro != null){
            
                if(filtro.getNome() != null && !filtro.getNome().isEmpty())
                    where += "nome like '%"+filtro.getNome() + "%'";

                if(filtro.getCpf() != null && !filtro.getCpf().isEmpty()){
                    if(where.length() > 0)
                        where += " and ";
                    where += "cpf = '"+filtro.getCpf().replace(".", "").replace("-", "") + "'";
                }
            }
            
            
            String consulta = "select * from Professores";
            
            if(where.length() >0 )
                consulta += " where " + where;
            
            consulta += " order by nome";
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement(consulta);
             
             ResultSet resultado = sql.executeQuery();
             
             List<Professor> professores = new ArrayList<>();
             
             while(resultado.next()) {
             
                Professor p = new Professor();
             
                p.setId( resultado.getInt("id"));
                p.setNome( resultado.getString("nome"));
                p.setCpf( resultado.getString("cpf"));
                
                professores.add(p);
             }
             return professores;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
}
