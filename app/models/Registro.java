package models;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Registro extends Model {
	
        public Long estacao_id;
        public String datahora;
        public float umidade;  
	public float temperatura;
        public float pressao;
        public float velocidade;
        public String hash;
}
