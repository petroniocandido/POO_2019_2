/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.DomainModel.Professor;
import br.edu.ifnmg.POO.DomainModel.Turma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author petronio
 */
public class TurmaRepositorio extends BancoDados {

    public TurmaRepositorio() {
        super();
    }
    
    public boolean Salvar(Turma turma){
        try {
            
            if(turma.getId() == 0){
                PreparedStatement sql = this.getConexao()
                        .prepareStatement("insert into Turmas(semestre, professor_id) values(?,?)",
                                Statement.RETURN_GENERATED_KEYS);

                sql.setString(1, turma.getSemestre());
                sql.setInt(2, turma.getProfessor().getId());

                if(sql.executeUpdate() > 0){ 
                    ResultSet chave = sql.getGeneratedKeys();
                    chave.next();
                    turma.setId(chave.getInt(1));
                    
                    insere_alunos(turma);
                    
                    return true;
                }
                else
                    return false;
            } else {
                PreparedStatement sql = this.getConexao()
                        .prepareStatement("update Turmas set semestre = ?, professor_id = ? where id = ?");

                sql.setString(1, turma.getSemestre());
                sql.setInt(2, turma.getProfessor().getId());
                sql.setInt(3, turma.getId());

                if(sql.executeUpdate() > 0) {
                    
                    PreparedStatement sql2 = this.getConexao()
                        .prepareStatement("delete from TurmasAlunos where id = ?");
                    
                    sql2.setInt(1, turma.getId());
                    
                    sql2.executeUpdate();
                    
                    insere_alunos(turma);
                    
                    return true;
                }
                else
                    return false;
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return false;
        
    }

    private void insere_alunos(Turma turma) throws SQLException {
        for(Aluno aluno : turma.getAlunos()){
            PreparedStatement sql2 = this.getConexao()
                    .prepareStatement("insert into TurmasAlunos(turma_id, aluno_id) values(?,?)");
            sql2.setInt(1, turma.getId());
            sql2.setInt(2, aluno.getId());
            sql2.executeUpdate();
        }
    }
    
    
    public Turma Abrir(int id){
        try {
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement("select * from Turmas where id = ?");
             
             sql.setInt(1, id);
             
             ResultSet resultado = sql.executeQuery();
             
             resultado.next();
             
             Turma turma = new Turma();
             
             turma.setId( resultado.getInt("id"));
             turma.setSemestre(resultado.getString("semestre"));
             
             ProfessorRepositorio prof_repo = new ProfessorRepositorio();
             
             Professor prof = prof_repo.Abrir(resultado.getInt("professor_id"));
             
             turma.setProfessor(prof);
             
             
             PreparedStatement sql2 = this.getConexao()
                     .prepareStatement("select aluno_id from TurmasAlunos where turma_id = ?");
             
             sql2.setInt(1, id);
             
             ResultSet resultado2 = sql2.executeQuery();
             
             AlunoRepositorio aluno_repo = new AlunoRepositorio();
             
             while(resultado2.next()){
                 Aluno aluno = aluno_repo.Abrir( resultado2.getInt("aluno_id") );
                 
                 turma.addAluno(aluno);
             }
             
             return turma;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    
}
