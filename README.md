# kotlin-graphql
SpringBoot MongoDB Kotlin GraphQL GraphiQL

From https://auth0.com/blog/building-graphql-apis-with-kotlin-spring-boot-and-mongodb/

local mongo with docker

docker run -d -p 27017-27019:27017-27019 --name local-mongo mongo:4.2-bionic

Embedded GraphiQL

http://localhost:9000/graphiql

graphql endpoint http://localhost:9000/graphql

query example

query {
  snacks {
    name,
    reviews {
      text, rating
    }
  }
}