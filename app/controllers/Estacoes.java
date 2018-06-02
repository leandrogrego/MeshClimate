package controllers;

import java.util.List;
import java.util.Random;

import models.Estacao;
import play.mvc.Controller;

public class Estacoes extends Controller{
	
	public static void adicionar(Estacao estacao) {
		render(estacao);
	}
	
	public static void salvar(Estacao estacao) {
                if(estacao.chave <= 0){
                    Random gerador = new Random();
                    estacao.chave=(gerador.nextInt()+100000)%100000;
                }
                
		estacao.save();
		listar();
	}
	
	public static void editar(Long id) {
            Estacao estacao = Estacao.findById(id);
            renderTemplate("Estacoes/adicionar.html", estacao);
	}
	
	public static void detalhes(Long id) {
            Estacao estacao = Estacao.findById(id);
            renderTemplate("Estacoes/detalhes.html", estacao);
            render(estacao);
	}
	
	public static void listar() {
		List<Estacao> estacoes = Estacao.findAll();
		render(estacoes);
	}
	
	public static void remover(Long id) {
		Estacao estacao = Estacao.findById(id);
		estacao.delete();
		listar();
	}
	
        public static void mapa(Long id) {
            Estacao estacao = Estacao.findById(id);
            render(estacao);
	}

}
