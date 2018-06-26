package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import models.Usuario;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.cache.Cache;
import play.libs.Mail;
import play.mvc.*;



public class Application extends Controller {
    Usuario login;
    
    
    public static void index(){
        if(checkLogin()==null){logout();}
        render();
    }
        
    public static Usuario checkLogin(){
        Usuario login = Cache.get(session.getId()+"-login", Usuario.class);
        return login;
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

    public void recuperaSenha(Usuario login) throws EmailException{
            flash.clear();
            if(login.email!=null){
                List<Usuario> users = Usuario.find("email = ?", login.email).fetch(1);
                if(users.size() > 0){
                    login = users.get(0);
                    SimpleEmail email = new SimpleEmail();
                    email.setFrom("meshclimae@resetnet.com.br");
                    email.addTo(login.email);
                    email.setSubject("Recupareção de senha");
                    email.setMsg("Olá"+ login.nome+".\n\n"
                        + "Vocçê solicitou umaa recuparação de senha.\n"
                        + "Sua senha para acesso ao MeshClimate: "
                        + login.password 
                        + "\n\n Equipe MeshClimate");
                    Mail.send(email); 
                    flash.success("<b>ATENÇÃO:</b> Foi enviado email de recuperação de senha para <b>"+login.email+".</n>");
                    renderTemplate("/application/login.html");
                } else {
                    flash.error("<b>"+login.email + "</b> não foi localizado no sistema!");
                }
            }
        render(login);
    }
}