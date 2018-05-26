# Introduction
Sample game project by Andrew Burk

# Getting Started
### Web Front End (requires node.js):  
Install the dependencies in the web directory by running `npm install`.  
Complile/transpile the source by running `npm run-script build`. This will build and place the output dist folder into the java resources folder.

### Web Server:  
In the Java solution, running Main run config will run Main.main(). This will launch the Javalin embedded Jetty server for the game web api and begin serving the static items in the dist directory from the previous step.
By default the server will be listening at http://localhost:7000
  
Navigate to http://localhost:7000 and you will be presented with the main game screen. It will start a new game by default. Play away!


# Development
### Web Front End (requires node.js):  
Start the webpack-dev-server with `npm start`. This will biuld the appropriate bundles/source maps and start the webpack dev server. By default the server will be listening at http://localhost:8080

### Web Server:  
In the Java solution, debug the Main run config. This will launch the Javalin embedded Jetty server for the game web api and allow debugging requests/responses.
By default the server will be listening at http://localhost:7000
  
Navigate to http://localhost:8080 and you will be presented with the main game screen. Open chrome devtools and debug away. Webpack dev server will live reload any changes made to the front end while it's running.