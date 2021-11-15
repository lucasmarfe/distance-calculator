# Getting Started with Distance Calculator API

The Distance Calculator API defines the REST API for calculating the sum of two distances.

This project was bootstrapped with [Spring Initializr](https://start.spring.io/).

## Prerequisites

* Java 11, Maven, Git

## Summary

* The Spring Boot framework was chosen for the development of the RESTful web service project because it is open source,
  enterprise-level for creating standalone, production-grade applications that run on the Java Virtual Machine (JVM).
* Domain-drive design was chosen because it is focused on the domain model. This way it encapsulates the business logic,
  getting business and code together.
* The goal of this project is converting distance measures between meters and yards and summing up
* The purpose of this project is to convert provided distances to meters or yards and add them up to get a distance in
  the desired unit. For the implementation of the conversion of units,
* The same pattern as used in the JDK Enum TimeUnit was
  used: [TimeUnit.java](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/TimeUnit.java)
  .
* Lombok library was used to minimize/remove the boilerplate code and save time by using some annotations
* Swagger was used as specification of the REST API, describing it. It can be used to share documentation, can also be
  used by some tools in order to automate API-related tests.

## Prerequisites

* Java 11, Maven, Git

## Endpoints

The API is documented using Swagger available on:
`http://localhost:8080/swagger-ui/index.html`

