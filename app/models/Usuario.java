package models;

import enums.Perfil;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
    public String nome;
    public String email;
    public String password;
    //public Blob foto;
    
    @Enumerated(EnumType.STRING)
    public Perfil perfil;
    
    @ManyToMany
    @JoinTable(name="usuario_estacao")
    public List<Estacao> estacao;
}

