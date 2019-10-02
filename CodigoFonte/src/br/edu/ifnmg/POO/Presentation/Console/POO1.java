/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Presentation.Console;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.Persistence.AlunoRepositorio;
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
       
        AlunoRepositorio repo = new AlunoRepositorio();
        
        List<Aluno> alunos = repo.Buscar(new Aluno("a","22222222222"));
        
        for(Aluno a : alunos)
            System.out.println(a.getNome());
       
    }
    
}
