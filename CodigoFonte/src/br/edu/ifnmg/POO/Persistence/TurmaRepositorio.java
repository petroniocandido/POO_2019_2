/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Persistence;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.DomainModel.ErroValidacaoException;
import br.edu.ifnmg.POO.DomainModel.Professor;
import br.edu.ifnmg.POO.DomainModel.Turma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class TurmaRepositorio extends BancoDados {

    public TurmaRepositorio() {
        super();
    }
    
    public List<String> listartSemestres() {
        List<String> semestres = new ArrayList<>();
        
        try {
            Statement sql = this.getConexao().createStatement();
            
            ResultSet resultado = sql.executeQuery("select distinct semestre from Turmas order by semestre");
            
            while(resultado.next()){
                semestres.add(resultado.getString("semestre"));
            }
 
            
        } catch (SQLException ex) {
            Logger.getLogger(TurmaRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return semestres;
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
                        .prepareStatement("delete from TurmasAlunos where turma_id = ?");
                    
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
             
             
            try {
                turma.setSemestre(resultado.getString("semestre"));
                turma.setId( resultado.getInt("id"));
                ProfessorRepositorio prof_repo = new ProfessorRepositorio();
                Professor prof = prof_repo.Abrir(resultado.getInt("professor_id"));
             
                turma.setProfessor(prof);
            } catch (ErroValidacaoException ex) {
                return null;
            }
                          
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
    
    
    
    public List<Turma> Buscar(Turma filtro){
        try {
            
            String where = "";
            
            if(filtro.getSemestre() != null && !filtro.getSemestre().isEmpty())
                where += "semestre = '"+filtro.getSemestre() + "'";
            
            if(filtro.getProfessor() != null){
                if(where.length() > 0)
                    where += " and ";
                where += "professor_id = " + filtro.getProfessor().getId();
            }
            
            
            String consulta = "select * from Turmas";
            
            if(where.length() >0 )
                consulta += " where " + where;
            
             PreparedStatement sql = this.getConexao()
                     .prepareStatement(consulta);
             
             ResultSet resultado = sql.executeQuery();
             
             List<Turma> turmas = new ArrayList<>();
             
             ProfessorRepositorio repo_prof = new ProfessorRepositorio();
             
             while(resultado.next()) {
             
                Turma p = new Turma();
             
                p.setId( resultado.getInt("id"));
                p.setSemestre( resultado.getString("semestre"));
                p.setProfessor(repo_prof.Abrir( resultado.getInt("professor_id") ));
                
                turmas.add(p);
             }
             return turmas;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ErroValidacaoException ex) {
            Logger.getLogger(TurmaRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
