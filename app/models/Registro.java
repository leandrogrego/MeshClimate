package models;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Registro extends Model {
	
        public Long estacao_ud;
        public Date data;
        public Time hora;
	public float temperatura;
        public float pressao;
        public float altitude;
        public float chuva;        
}
