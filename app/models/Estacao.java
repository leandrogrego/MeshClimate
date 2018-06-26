package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Estacao extends Model {
	
        public String nome;
	public String status;
        public String latitude;
        public String longitude;
        public int chave;
        
        @ManyToMany(mappedBy="estacao")
	public List<Usuario> usuarios;
        
        @OneToMany
        @JoinColumn(name="estacao_id")
        public List<Registro> registros;
        
}
