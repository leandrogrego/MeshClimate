package controllers;

import static controllers.Application.checkLogin;
import static controllers.Application.logout;
import enums.Perfil;
import java.util.Arrays;
import java.util.List;
import models.Estacao;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Usuarios extends Controller{
    Usuario login;
    
    @Before
    public void logado() {
        login = checkLogin();
    }
    
    public static void adicionar(Usuario usuario) {
        if(checkLogin().perfil!=Perfil.ADMINISTRADOR){
            flash.error("Você não tem permissão para usar esta função!");
            Application.index();
        }
        
        List<Estacao> estacoes = Estacao.findAll();
        Perfil[] perfis = Perfil.values();
        render(estacoes, usuario, perfis);
    }
    
    public static void salvar(Usuario usuario, String estacoesIDs[]) {
        if(checkLogin().perfil!=Perfil.ADMINISTRADOR){
            flash.error("Você não tem permissão para usar esta função!");
            Application.index();
        }
        if(estacoesIDs == null || estacoesIDs.length == 0) {
            usuario.estacao = null;
        }else {
            String IDs;
            IDs = Arrays.toString(estacoesIDs);
            IDs = IDs.replace("[","(");
            IDs = IDs.replace("]",")");
            System.out.println(IDs);
            String query = "SELECT e FROM Estacao e WHERE e.id IN "+IDs;
            List<Estacao> userestacoes = Estacao.find(query).fetch();
            usuario.estacao = userestacoes;
        }  
        usuario.save();
        flash.success("Dados da Estação salvos com sucesso!");
        detalhes(usuario.id);
    }

    public static void editar(Long id) {
        if(checkLogin().perfil!=Perfil.ADMINISTRADOR){
            flash.error("Você não tem permissão para usar esta função!");
            Application.index();
        }
        
        Usuario usuario = Usuario.findById(id);
        List<Estacao> estacoes = Estacao.findAll();
        for(Estacao estacao : usuario.estacao){
            estacoes.remove(estacao);
        }
        Perfil[] perfis = Perfil.values();
        renderTemplate("Usuarios/adicionar.html", usuario, estacoes, perfis);
    }
	   
    public static void detalhes(Long id) {
        if(checkLogin().perfil!=Perfil.ADMINISTRADOR || checkLogin().perfil!=Perfil.SUPERVISOR){
            flash.error("Você não tem permissão para usar esta função!");
            Application.index();
        }
        
        Usuario usuario = Usuario.findById(id);
        render(usuario);
    }
    
    public static void listar() {
	List<Usuario> usuarios = Usuario.findAll();
        render(usuarios);
    }
    
    public static void remover(Long id) {
        if(checkLogin().perfil!=Perfil.ADMINISTRADOR){
            flash.error("Você não tem permissão para usar esta função!");
            Application.index();
        }
        
        Usuario usuario = Usuario.findById(id);
        usuario.delete();
        listar();
    }
}
