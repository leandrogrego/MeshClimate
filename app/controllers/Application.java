package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import models.Usuario;
import play.*;
import play.cache.Cache;
import play.mvc.*;



public class Application extends Controller {
    public static void index() {
        if(!checkLogin()){logout();}
        render();
    }
        
    public static boolean checkLogin(){
        Usuario login = Cache.get(session.getId()+"-login", Usuario.class);
        return (login!=null);
    }
    
    public static void login(Usuario login){
        List<Usuario> users = Usuario.find("email = ? and password = ?", login.email, login.password).fetch();
        if(users.size() > 0){
            login = users.get(0);
            Cache.set(session.getId()+"-login", login, "30mn");
            if(login == null){
                logout();
            }else{
                flash.error("");
                System.out.println("Login de: "+login.email);
                renderTemplate("Application/index.html");
            }
        } else{
            flash.error("Login inválido!");
        }
        render(login);
    }

    public static void logout(){
        Cache.set(session.getId()+"-login", null);
        renderTemplate("Application/login.html");
    }
    
    public static String hashMD5(String s) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] hash = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
           if ((0xff & hash[i]) < 0x10)
              hexString.append("0"+Integer.toHexString((0xFF & hash[i])));
           else
              hexString.append(Integer.toHexString(0xFF & hash[i]));
         }
         s = hexString.toString();
        return s;
    }

}