# userwsjwtswagger
Rest api para creacion de usuarios y atenticacion con JWT -spring boot - swagger

#pruebas con swagger en Autentificación Auth Rest Controller

http://localhost:8090/swagger-ui/

-/auth/signup creacion de usuarios y otencion de token de acceso(El usuario se crea con el rol USER)

-/auth/signin iniciar sesión

#Pruebas de roles en Prueba Test Rest Controller

-puede probar el acceso a las rutas /test/all allAccess->sin autenticacion /test/user userAccess ->usuario creado las demas rutas no pueden accederse debido a que no tienen el rol

#Base de datos H2 en memoria http://localhost:8090/h2-ui

en el campo JDBC URL poner el valor jdbc:h2:mem:testdb JDBC URL: jdbc:h2:mem:testdb

Todos los usuarios por defecto se crean con el rol ROLE_USER.

Para probar los demas roles vaya a la pagina de la base de datos H2 y cree los roles usando el sigueinte script:<br>
insert into ROLES values(1,'ROLE_USER);<br>
insert into ROLES values(2,'ROLE_ADMIN');<br>
insert into ROLES values(3,'ROLE_SUPERVISOR');<br>
insert into ROLES values(4,'ROLE_MODERATOR');<br>

Para crear un usuario siga los siguientes pasos:<br>
1)vaya a la seccion de "Autenticación Auth Rest Controller" y escoja la opcion "POST ​/auth​/signup Registro de usuarios"<br>
2) click sobre el botón "Try it out"<br>
3)dificque los datos del json en los campos email y password <br>
4)de click en el botón "Execute", si todo ha ido bien creara el usuario con el rol ROLE_USER y devolvera los datos del usuario junto con un token para autenticarse<br>
copie el token, lo usara para autorizar y probar los permisos sobre las rutas<br>
5) si desea iniciar sesion con el usuario creado vaya a POST /auth​/signin y escola la opcion "Try it out", cambie el usuario con el correo del usuario creado y tambien cambie la contraseña, dele clcick a la opcion "Execute", copie el token.<br>

6)para autorizacion vaya a la parte superior y de click sobre  el botón "Authorize", de click y en el campo "Value" escriba la pabara "Bearer", + un espacio " " y pegue el token devuelto al crear usuario o al iniciar sesión, finalmente de click en "Authorize"<br>
7)pruebe cada una de las rutas y verifique que solo permite el acceso al contenido publico y al de user.<br>
8) para probar los demas roles cree un usuario para cada uno y actualice el rol en la tabla USERS_ROLES mediante un update del numero del rol en la pagina de H2 , segun el rol que desee asignarle.<br>
9)pruebe los acceso a cada rol.
