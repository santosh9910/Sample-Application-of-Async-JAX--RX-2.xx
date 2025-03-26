package com.example.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api") // Base path for your API
public class JaxrsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // Register your JAX-RS resources here
        classes.add(com.example.resource.HelloResource.class);
        return classes;
    }
}