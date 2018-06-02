package controllers;

import java.util.List;
import java.util.Random;

import models.Estacao;
import play.mvc.Controller;

public class Registros extends Controller{
	
	public static void salvar(Estacao estacao) {
        	estacao.save();
		listar();
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
	
}
