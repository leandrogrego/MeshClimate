package controllers;

import static controllers.Application.checkLogin;
import static controllers.Application.logout;
import static controllers.Registros.listar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import models.Estacao;
import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;

public class Usuarios extends Controller{
    
    public static void adicionar(Usuario usuario) {
        if(!checkLogin()){logout();}
        render(usuario);
    }
    
    public static void salvar(Usuario usuario) {
        if(!checkLogin()){logout();}
        usuario.save();
        flash.success("Dados da Estação salvos com sucesso!");
        detalhes(usuario.id);
    }

    public static void editar(Long id) {
        if(!checkLogin()){logout();}
        Usuario usuario = Usuario.findById(id);
        renderTemplate("Usuarios/adicionar.html", usuario);
    }
	   
    public static void detalhes(Long id) {
        if(!checkLogin()){logout();}
        Usuario usuario = Usuario.findById(id);
        render(usuario);
    }
    
    public static void listar() {
        if(!checkLogin()){logout();}
	List<Usuario> usuarios = Usuario.findAll();
        render(usuarios);
    }
    
    public static void remover(Long id) {
        if(!checkLogin()){logout();}
        Usuario usuario = Usuario.findById(id);
        usuario.delete();
        listar();
    }
}
