package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Registro extends Model {
	
        
        public String datahora;
        public float umidade;  
	public float temperatura;
        public float pressao;
        public float velocidade;
        public String hash;
        //@JoinColumn(name="estacao_id")
//      @ManyToOne
        public Long estacao_id;
}
