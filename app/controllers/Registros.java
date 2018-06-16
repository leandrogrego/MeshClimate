package controllers;

import static controllers.Application.checkLogin;
import static controllers.Application.logout;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import models.Estacao;
import models.Registro;
import play.mvc.Controller;

public class Registros extends Controller{
	
	
        public static void registrar(Long estacao_id, String datahora, float temperatura, float umidade, float velocidade, float pressao, String hash) throws NoSuchAlgorithmException{
            Registro registro = new Registro();
            registro.estacao_id = estacao_id;
            registro.datahora = datahora;
            registro.temperatura = temperatura;
            registro.umidade = umidade;
            registro.velocidade = velocidade;
            registro.pressao = pressao;
            registro.hash = hash;
            salvar(registro);
            render(registro.hash+"<br>"+hash);
        }
    
        public static void salvar(Registro registro) throws NoSuchAlgorithmException {
            Estacao estacao = Estacao.findById(registro.estacao_id);
            String hash = Application.hashMD5(""+registro.estacao_id+registro.datahora+estacao.chave);
            if(hash.equals(registro.hash)){
                registro.save();
            }
	}
	
	public static void listar(){
            if(!checkLogin()){logout();}
            List<Registro> registro = Registro.findAll();
            render(registro);
	}
        
        public static void listarEstacao(Long estacao_id) {
            if(!checkLogin()){logout();}
		List<Registro> registros = Registro.find("byEstacao_id", estacao_id).fetch();
		render(registros);
	}
	
	public static void remover(Long id) {
            if(!checkLogin()){logout();}
		Registro registro = Registro.findById(id);
		registro.delete();
		listar();
	}

        public static void feeder(Registro registro) {
		render();
	}
	
}
