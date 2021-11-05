package com.gremo.springbootconcourse;

import com.gremo.springbootconcourse.books.model.Book;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class BookRestTest {
    // trigger ci
    @BeforeEach
    public void setup(){
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testAddBook(){
        Book book = new Book("94747820", "Claude Kibaya", "Shitty Friends", 120);

        given().
                contentType("application/json").
                body(book).
                when().
                post("/books").
                then().
                statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("/books/94747820")
                .then()
                .contentType("application/json")
                .and()
                .body("author",equalTo("Claude Kibaya"))
                .body("title",equalTo("Shitty Friends"));
        //cleanup
        given().when().delete("/books/94747820");
    }

    @Test
    public void testUpdateBook(){
        Book book = new Book("94747820", "Claude Kibaya", "Shitty Friends", 120);

        given().
                contentType("application/json").
                body(book).
                when().
                post("/books").
                then().
                statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("/books/94747820")
                .then()
                .contentType("application/json")
                .and()
                .body("author",equalTo("Claude Kibaya"))
                .body("title",equalTo("Shitty Friends"));

        // update
        book.setTitle("Best Friends");
        given().
                contentType("application/json").
                body(book).
                when().
                put("/books/94747820").
                then().
                statusCode(200);

        // get the book and verify
        given()
                .when()
                .get("/books/94747820")
                .then()
                .contentType("application/json")
                .and()
                .body("author",equalTo("Claude Kibaya"))
                .body("title",equalTo("Best Friends"));
        //cleanup
        given().when().delete("/books/94747820");
    }

    @Test
    public void testDeleteBook(){
        Book book = new Book("94747820", "Claude Kibaya", "Shitty Friends", 120);

        given().
                contentType("application/json").
                body(book).
                when().
                post("/books").
                then().
                statusCode(200);

        //cleanup
        given().when().delete("/books/94747820");

        // get the book and verify
        given()
                .when()
                .get("/books/94747820")
                .then()
                .statusCode(404)
                .and()
                .body("message",equalTo("Book with isbn 94747820 is not found"));
    }

    @Test
    public void testGetBook(){
        Book book = new Book("94747820", "Claude Kibaya", "Shitty Friends", 120);

        given().
                contentType("application/json").
                body(book).
                when().
                post("/books").
                then().
                statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("/books/94747820")
                .then()
                .contentType("application/json")
                .and()
                .body("author",equalTo("Claude Kibaya"))
                .body("title",equalTo("Shitty Friends"));
        //cleanup
        given().when().delete("/books/94747820");
    }

    @Test
    public void testGetAllBooks(){
        Book book1 = new Book("94747820", "Claude Kibaya", "Shitty Friends", 120);
        Book book2 = new Book("83939202", "Moses Kalema", "Loud Friends", 200);

        given().
                contentType("application/json").
                body(book1).
                when().
                post("/books").
                then().
                statusCode(200);

        given().
                contentType("application/json").
                body(book2).
                when().
                post("/books").
                then().
                statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("/books")
                .then()
                .contentType("application/json")
                .and()
                .body("author",hasItems("Claude Kibaya", "Moses Kalema"))
                .body("title",hasItems("Shitty Friends","Loud Friends" ));
        //cleanup
        given().when().delete("/books/94747820");
        given().when().delete("/books/83939202");
    }

    @Test
    public void testSearchBook(){
        Book book = new Book("94747820", "Claude", "Shitty Friends", 120);

        given().
                contentType("application/json").
                body(book).
                when().
                post("/books").
                then().
                statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("/books/search/Claude")
                .then()
                .contentType("application/json")
                .and()
                .body("author",equalTo("Claude"))
                .body("title",equalTo("Shitty Friends"));
        //cleanup
        given().when().delete("/books/94747820");
    }
}
