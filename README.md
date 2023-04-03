# note-aplication
Java 17
Spring boot 2.7.1
Maeven 4.0.0
MySql WorkBench 8.0
Postman 9.23
how to use it:

download project

create a data base "notes"

configure "aplication.properties" with your data base configuration: for example: spring.datasource.url=jdbc:mysql://localhost:3306/notes spring.datasource.username=root spring.datasource.password=root

install lombock

run EnsolversNotesChallengeAplication.java

in Postman auth/register : create a user (userName is an email , password must have minimum 8 characters)

auth/login :login the user and copy jwt

now you can use the api functions adding the jwt in the "authorization" header

the user you use depends on the jwt use in header if you have to use another user have to login and use jwt.
