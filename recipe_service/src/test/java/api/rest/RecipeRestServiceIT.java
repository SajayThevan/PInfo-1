package api.rest;


import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import io.restassured.http.ContentType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;

import org.h2.util.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import api.rest.RecipeRestService;
import domain.model.Comment;
import domain.model.Ingredient;
import domain.model.Recipe;
import domain.service.RecipeService;

public class RecipeRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/recipe";
		RestAssured.port = 8080;
	}
	
	
	//@InjectMocks
	//KeycloakService KeycloakService;

	@Test
	public void testPostRecipe() {
		
		Recipe recipe = new Recipe();
		// JsonObject recipejson = Json.createObjectBuilder()
		// 		.add("name", "name")
		// 		.add("picture", "picture")
		// 		.add("nbPersons", 5)
		// 		.add("preparationTime", 523)
		// 		.add("difficulty", 642)
		// 		.add("ingredients", Json.createArrayBuilder()
		// 				.add(Json.createObjectBuilder()
		// 						.add("quantity",296)
		// 						.add("detailsID", 6))
		// 				.add(Json.createObjectBuilder()
		// 						.add("quantity",269)
		// 						.add("detailsID", 834)))
		// 		.add("preparation", "preparation")
		// 		.add("author", "author")
		// 		.add("publicationData",15400)
		// 		.add("grade", 3.5)
		// 		.add("comments", Json.createArrayBuilder()
		// 				.add("comment"))
		// 		.build();
		recipe = getRandomRecipe();
		// long id = recipe.getId();
		// given()
		// .contentType(ContentType.JSON)
		// .body(recipejson)
		// .when()
		// .post("/")
		// .then()
		// .statusCode(200);
		with().contentType(ContentType.JSON).body(recipe).when().request("POST","/").then().statusCode(200);
	}

	@Test
	public void testTest() {
		when().get("/").then().body(containsString("1"));
	}
	/*
	@Test
	public void testPostComment() {
		when().get("/1").then().body(containsString("Abricot"));
		when().get("/1").then().body(containsString("vegan"));
	}
	@Test
	public void testDeleteComment() {
		
		recipeService.create(getRandomRecipe());
		List<Recipe> recipes = recipeService.getAllRecipes();
		Recipe myRecipe = recipes.get(0);
		Comment comment1 = recipeService.createComment("bonjour c'est moyen", "asdfakasy", (short)3);
		Comment comment2 = recipeService.createComment("bonjour je suis content", "lasdfasdf", (short)5);
		List<Comment> listComment = new ArrayList<Comment>();
		listComment.add(comment1);
		listComment.add(comment2);
		myRecipe.setComments(listComment);
		RecipeService.createComment(text, userID, grade);
		RecipeRestService.deleteComment(idRecipe, idComment, headers);
		
		when().get("/1").then().body(containsString("Abricot"));
		when().get("/1").then().body(containsString("vegan"));
	}
	
*/

	private Ingredient getRandomIngredient() {
		Ingredient ingredient = new Ingredient();
		ingredient.setDetailsID((long) (Math.random()*1000));
		ingredient.setQuantity((short) (Math.random()*1000));
		
		return ingredient;
	}
	
	private Comment getRandomComment() {
		Comment comment = new Comment();
		comment.setText(UUID.randomUUID().toString());
		comment.setUserID(UUID.randomUUID().toString());
		comment.setGrade((short)(Math.random() * ((5 - 0) + 1)));
		
		return comment;
	}
	
	private Recipe getRandomRecipe() {
		
		List<Ingredient> listIng = new ArrayList<Ingredient>();
		Ingredient ingredient1 = getRandomIngredient();
		Ingredient ingredient2 = getRandomIngredient();
		listIng.add(ingredient1);
		listIng.add(ingredient2);
		
		Comment comment1 = getRandomComment();
		Comment comment2 = getRandomComment();
		List<Comment> listComment = new ArrayList<Comment>();
		listComment.add(comment1);
		listComment.add(comment2);
		
		Recipe i = new Recipe();
		i.setName(UUID.randomUUID().toString());
		i.setPreparationTime((short) (Math.random()*1000));
		i.setDifficulty((short) (Math.random()*1000));
		i.setNbPersons((short) (Math.random()*1000));
		i.setPicture(UUID.randomUUID().toString());
		i.setPreparation(UUID.randomUUID().toString());
		i.setAuthor(UUID.randomUUID().toString());
		i.setPublicationDate(Date.valueOf("2019-01-26"));
		i.setGrade((float) (Math.random()*1000));
		i.setComments(listComment);
		i.setIngredients(listIng);

		
		return i;
	}
	
	
}