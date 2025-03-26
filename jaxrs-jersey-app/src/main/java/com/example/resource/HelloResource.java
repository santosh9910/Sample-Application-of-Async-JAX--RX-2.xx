package com.example.resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello from JAX-RS with Jersey on GlassFish!";
    }

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting getGreeting() {
        return new Greeting("Hello------->", "World");
    }
    
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @GET
    @Path("/h1")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletableFuture<Response> asyncHello() {
    	Thread.dumpStack();
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulating a delay
                return Response.ok("{\"message\": \"Hello, World!\"}").build();
            } catch (InterruptedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity("{\"error\": \"Something went wrong\"}")
                                .build();
            }
        }, executor);
    }
}

class Greeting {
    private String message;
    private String name;

    public Greeting() {}

    public Greeting(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}