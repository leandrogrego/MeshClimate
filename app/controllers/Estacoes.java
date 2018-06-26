package controllers;

import static controllers.Application.checkLogin;
import static controllers.Application.logout;
import java.util.List;
import java.util.Random;

import models.Estacao;
import models.Registro;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Estacoes extends Controller{
    Usuario login;
    @Before
    public void antes(Usuario usuario) {
        login = checkLogin();
        if(login==null){logout();}
    }
    
	public static void adicionar(Estacao estacao) {
            if(checkLogin()==null){logout();}
            render(estacao);
	}
	
	public static void salvar(Estacao estacao) {
            if(checkLogin()==null){logout();}
            if(estacao.chave <= 0){
                Random gerador = new Random();
                int chave = gerador.nextInt()%100000;
                if (chave<=0) {chave = (chave+100000)%100000;}
                estacao.chave=chave;
            }
            estacao.save();
            flash.success("Dados da Estação salvos com sucesso!");
            detalhes(estacao.id);
	}
	
	public static void editar(Long id) {
            if(checkLogin()==null){logout();}
            Estacao estacao = Estacao.findById(id);
            renderTemplate("Estacoes/adicionar.html", estacao);
	}
	
	public static void detalhes(Long id) {
            if(checkLogin()==null){logout();}
            Estacao estacao = Estacao.findById(id);
            List<Registro> registros = Registro.find("Estacao_id = ? order by datahora DESC", estacao.id).fetch(25);
            render(estacao, registros);
	}
	
	public static void listar() {
            if(checkLogin()==null){logout();}
		List<Estacao> estacoes = Estacao.findAll();
		render(estacoes);
	}
	
	public static void remover(Long id) {
            if(checkLogin()==null){logout();}
		Estacao estacao = Estacao.findById(id);
		estacao.delete();
		listar();
	}
	
        public static void mapa(Long id) {
            if(checkLogin()==null){logout();}
            Estacao estacao = Estacao.findById(id);
            render(estacao);
	}
}