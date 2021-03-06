package domain.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="Recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	private String name;
	private String picture;
	private short nbPersons;
	private short preparationTime;
	private short difficulty;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;
	private String preparation;

	private String author;

	private Date publicationDate;
	

	private float grade;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	

	
}
