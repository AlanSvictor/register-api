package br.com.register.api.graphql.resolvers.queries;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    public String hello (DataFetchingEnvironment dataFetchingEnvironment) {
        return "hello world! welcome graphQL alan!";
    }
}
