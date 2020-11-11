
## Basic Restful Java EE 8 project for proof of concept
based on project by Luqman Saeed from https://www.udemy.com/course/java-enterprise-edition-8

"So we made a Java EE 8 UberJar. We ran 'java -jar hello-todo.jar' and it
runs automatically on Payara Micro server. This is roughly similar to what Spring Boot does,
but this is Java EE 8."

#### created maven profile in POM file to run program on Payara Micro server from IDE
use "mvn package payara-micro:start"

Got it running but seems logs seem sketchy, definitely not stable
[2020-11-11T07:19:51.521-0600] [] [INFO] [] [PayaraMicro] [tid: _ThreadID=1 _ThreadName=main] [timeMillis: 1605100791521] [levelValue: 800]
Payara Micro URLs:
http://192.168.1.122:8080/
http://192.168.1.122:8080/hello-todo

'ROOT' REST Endpoints:
GET     /api/v1/application.wadl
GET     /api/v1/todo/list
POST    /api/v1/todo/new
POST    /api/v1/todo/status
PUT     /api/v1/todo/update
GET     /api/v1/todo/{id}
GET     /openapi/
GET     /openapi/application.wadl

'hello-todo' REST Endpoints:
GET     /hello-todo/api/v1/application.wadl
GET     /hello-todo/api/v1/todo/list
POST    /hello-todo/api/v1/todo/new
POST    /hello-todo/api/v1/todo/status
PUT     /hello-todo/api/v1/todo/update
GET     /hello-todo/api/v1/todo/{id}



[2020-11-11T07:19:51.522-0600] [] [INFO] [] [PayaraMicro] [tid: _ThreadID=1 _ThreadName=main] [timeMillis: 1605100791522] [levelValue: 800] Payara Micro
  5.182 #badassmicrofish (build 303) ready in 29,729 (ms)



#### local build with Payara Microserver 5 on Windows 10
// run this to run payara micro server (which the payara jar must be in the IdeaProjects folder, downloaded from payara micro server 5 online)
// persists basic entity Manager in memory - no MySQL database set up, but also data is not persisted
java -jar payara-micro.jar --deploy C:\Users\{user}\IdeaProjects\hello-todo\target\hello-todo.war

#### Create UberJar to package project as .jar file to run anywhere
// Create UberJar --> package our project into a single .jar file that can be called or executed anywhere
// ** note we defined (arbitrarily) port 8088 for this jar to run on - default 8080 won't work

C:\Users\{user}\IdeaProjects>java -jar payara-micro.jar --deploy C:\Users\{user}\IdeaProjects\hello-todo\target\hello-todo.war --port 8088 --outputUberJar helloTodo.jar
Nov 11, 2020 5:36:21 AM fish.payara.micro.impl.UberJarCreator buildUberJar
INFO: Building Uber Jar... helloTodo.jar
Nov 11, 2020 5:36:21 AM fish.payara.micro.impl.UberJarCreator buildUberJar
INFO: Built Uber Jar C:\Users\{user}\IdeaProjects\helloTodo.jar in 756 (ms)

#### now can run "java -jar helloTodo.jar" to run application
// now can run "java -jar helloTodo.jar" to run application on localhost:8088/hello-todo/api/v1/new
// localhost:8088/hello-todo/api/v1/list
// localhost:8088/hello-todo/api/v1/update
// etc. various defined endpoints
// this runs a Java EE8 UberJar








# Build
mvn clean package && docker build -t com.kwgdev/hello-todo .

# RUN

docker rm -f hello-todo || true && docker run -d -p 8080:8080 -p 4848:4848 --name hello-todo com.kwgdev/hello-todo 