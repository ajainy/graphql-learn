package com.snapcx.graphql.learn;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;


import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@SpringBootApplication
@RestController
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}
	
	
	@RequestMapping("/hello")
	public String helloEndpoint() {
	  try {
	    
	    GraphQLObjectType queryType = 
	        newObject()
          .name("helloWorldQuery")
          .field(newFieldDefinition().type(GraphQLString).name("hello").staticValue("world"))
          .build();

  GraphQLSchema schema = GraphQLSchema.newSchema()
          .query(queryType)
          .build();

  GraphQL graphQL = GraphQL.newGraphQL(schema).build();

  Map<String, Object> result = graphQL.execute("{hello}").getData();
  return result.toString();
	    
	    
	  } catch (Exception ex) {
	    ex.printStackTrace();
	    return ex.getMessage();
	  }
	}
}
