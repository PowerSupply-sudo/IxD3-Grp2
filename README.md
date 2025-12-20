Following repo is developed as part of a semester project at Aalborg University Department of Computer Science.
Line: Interactions Design (IxD)
main: Main branch and the up to date relased version of the program.

Disregard me


how to run: 

(IMPORTANT: if MySQL workbench is installed, it occupies the server with the port "3000" on startup. go services.msc, find MySQL94 and change startup from automatic to manual. close the running instance as well.)

1: start up docker by writing the following while in the root directory "avarice p3\IxD3-P2": 
  1.1: "cd src/db"
  1.2: "docker compose up -d"
2. go to the root directory "avarice p3\IxD3-P2" and type the following: "mvn javafx:run"
  2.1 username and password: 
  "test"
  "1234"
