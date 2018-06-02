package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Estacao extends Model {
	
        public String nome;
	public String sstatus;
        public String latitude;
        public String longitude;
        public int chave;
        
}
