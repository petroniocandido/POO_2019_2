/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Presentation.Console;

import br.edu.ifnmg.POO.DomainModel.Aluno;
import br.edu.ifnmg.POO.Persistence.AlunoRepositorio;
import java.io.IOException;

/**
 *
 * @author petronio
 */



public class POO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        AlunoRepositorio repo = new AlunoRepositorio();
        
        /*
        Aluno pc = new Aluno();
       pc.setNome("Petrônio");
       pc.setCpf("12345678901");
       
        
        
        System.out.println(repo.Salvar(pc));
        */
        
        Aluno pc = repo.Abrir(1);
        
        System.out.println(pc.getNome());
    }
    
}
