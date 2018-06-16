package controllers;

import static controllers.Application.checkLogin;
import static controllers.Application.logout;
import java.util.List;
import java.util.Random;

import models.Estacao;
import models.Registro;
import play.mvc.Controller;

public class Estacoes extends Controller{
	
	public static void adicionar(Estacao estacao) {
            if(!checkLogin()){logout();}
            render(estacao);
	}
	
	public static void salvar(Estacao estacao) {
            if(!checkLogin()){logout();}
            if(estacao.chave <= 0){
                Random gerador = new Random();
                estacao.chave=(gerador.nextInt()+100000)%100000;
            }
            estacao.save();
            flash.success("Dados da Estação salvos com sucesso!");
            detalhes(estacao.id);
	}
	
	public static void editar(Long id) {
            if(!checkLogin()){logout();}
            Estacao estacao = Estacao.findById(id);
            renderTemplate("Estacoes/adicionar.html", estacao);
	}
	
	public static void detalhes(Long id) {
            if(!checkLogin()){logout();}
            Estacao estacao = Estacao.findById(id);
            List<Registro> registros = Registro.find("Estacao_id = ? order by datahora DESC", estacao.id).fetch(10);
            render(estacao, registros);
	}
	
	public static void listar() {
            if(!checkLogin()){logout();}
		List<Estacao> estacoes = Estacao.findAll();
		render(estacoes);
	}
	
	public static void remover(Long id) {
            if(!checkLogin()){logout();}
		Estacao estacao = Estacao.findById(id);
		estacao.delete();
		listar();
	}
	
        public static void mapa(Long id) {
            if(!checkLogin()){logout();}
            Estacao estacao = Estacao.findById(id);
            render(estacao);
	}
}