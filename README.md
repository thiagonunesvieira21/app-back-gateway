### SERVIDOR DE APLICAÇÃO ###
- Tomcat 8
    - Configuracão datasource:
          - Arquivo ../conf/context.xml  e adicione:
              <Resource name="jdbc/PostgresDS" 
                      global="jdbc/PostgresDS" 
                      auth="Container"
                      type="javax.sql.DataSource" 
                      driverClassName="org.postgresql.Driver" 
                      url="jdbc:postgresql://localhost:5432/posdes01" 
                      username="app" 
                      password="app"   
                      maxIdle="20" 
                      minIdle="5" /> 
                      
           - Acione o jar /resources/postgresql-9.4-1205.jdbc42.jar na pasta ../lib/
        
## BANCO DE DADOS ###
- Postgres
    - create user "app" com senha "app"
    
### INSTALAR REDIS ###

- sudo apt-get install redis-server
- Comando para apagar todos registros do Redis: redis-cli keys '*'| xargs redis-cli del

MAC Instalação/Start/Stop

    brew install redis
    redis-server /usr/local/etc/redis.conf

### ENDEREÇO SWAGGER ###
- http://localhost:8080/swagger-ui.html

Logue com o seu nome de usuário thiago.vieira
Sua senha provisória é 3fd4b097
