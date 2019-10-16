/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Presentation.Console;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.DomainModel.Professor;
import br.edu.ifnmg.POO.DomainModel.Sexo;
import br.edu.ifnmg.POO.DomainModel.Turma;
import br.edu.ifnmg.POO.Persistence.AlunoRepositorio;
import br.edu.ifnmg.POO.Persistence.ProfessorRepositorio;
import br.edu.ifnmg.POO.Persistence.TurmaRepositorio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petronio
 */



public class POO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       
        AlunoRepositorio aluno_repo = new AlunoRepositorio();
        ProfessorRepositorio prof_repo = new ProfessorRepositorio();
        TurmaRepositorio turma_repo = new TurmaRepositorio();
        
        /*
        List<Aluno> alunos = new ArrayList<>();
        
        for(int a = 0; a < 10; a++){
            Aluno aluno = new Aluno(Integer.toString(a), Integer.toString(a));
            aluno.setSexo(Sexo.F);
            aluno_repo.Salvar(aluno);
            alunos.add(aluno);
        }
        
        Professor prof = new Professor();
        prof.setNome("Petrônio");
        prof.setCpf("33");
        prof_repo.Salvar(prof);
        
        Turma turma = new Turma();
        
        turma.setSemestre("20192");
        turma.setProfessor(prof);
        turma.setAlunos(alunos);
        
        turma_repo.Salvar(turma);

*/
        
        Turma t = turma_repo.Abrir(1);
        
        System.out.println(t.getProfessor().getNome());
        
        for(Aluno a : t.getAlunos())
            System.out.println(a.getNome());

    }
    
}
