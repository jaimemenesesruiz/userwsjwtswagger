# Registro de usuarios e inicio de sesion usando JWT<br>
Rest api para creacion y autenticacion de usuarios mediante JWT.<br>
Esta api fue creada usando spring boot, H2, jpa , JWT y Swagger.<br>

1)Para ver las tablas y sus valores peude ingresar a la url http://localhost:8090/h2-ui/  y cambiar el valor del campo "JDBC URL" por jdbc:h2:mem:testdb
y dar click en el boton "conectar".<br>
Ahi se pueden ver los roles, usuarios, usuarios por rol y telefonos, en la tablas ROLES,USERS,USERS_ROLES y PHONES respectivamente.<br>
Para ver el contenido de cada tabla puede dar clic en el nombre de la tabla y dar click sobre el boton Run. <br>
una vez haya visto lo que requiere ver de dicha tabla debe dar clic en el boton clear para limpiar la consulta antes de volver a dar click en otra tabla que quiera ver.<br>

2)Registro de usuarios: crea un usuario con el username:name@test.com y password:M2test y rol USER<br>
NOTA: Todos los usuarios nuevos se crean con rol USER.<br>
peticion:curl -X POST "http://localhost:8090/auth/signup" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"email\": \"name@test.com\", \"name\": \"name\", \"password\": \"M2test\", \"phones\": [ { \"citycode\": \"1\", \"contrycode\": \"20\", \"number\": \"3002108154\" } ]}"<br>
Status:200<br>
resultado:{<br>
  "id": "103f725f-e402-461b-8722-bf4aff7753b0",<br>
  "created": "Sun Apr 16 15:11:54 COT 2023",
  "modified": "null",
  "lastLogin": "Sun Apr 16 15:11:54 COT 2023",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW1lQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc1OTE1LCJleHAiOjE2ODE2Nzk1MTV9.fop32GgvSGADp6xH4XgnHxuEH8-Gv_JF1mH-IcP_yXgue8JN0PHkCvmYdE2e2o2x1KG6rKPQ6WsniW6DR0eJyw",
  "active": true
}<br>

3)inicio de sesion
NOTA:Se puede iniciar sesion con el usuario recien creado, usando el correo como username y la contraseña M2test, tambien lo puede hacer con los usuarios creados por defecto al iniciar la aplicacion: los usuario son: user@test.com, admin@test.com, supervisor@test.com y moderator@test.com, con los roles USER,ADMIN, SUPERVISOR y MODERATOR repectivamente, todos tienen la misma contraseña M2test.
Cuando haya iniciado sesion, debera copiar el valor del campo "token" sin incluir los caracteres ", en este ejemplo el valor es eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW1lQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc2MDk3LCJleHAiOjE2ODE2Nzk2OTd9.FXOeqW9mNdIw9xYGspWaZuK2veOkK0YFNiorW5ZFUwLagdgVNA7f93WHPT2t9hX06ChGGwMJ4Bvc3iE65xLIvw  , ese token se debe enviar en los headers de cada peticion, con el header Authorization y concatenado con la palabra Bearer y un espacio, como se ve en los numerales 4.2 en adelante.<br>
peticion:curl -X POST "http://localhost:8090/auth/signin" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"password\": \"M2test\", \"username\": \"name@test.com\"}"
Status:200<br>
Resultado:{
  "id": "103f725f-e402-461b-8722-bf4aff7753b0",
  "created": "2023-04-16 15:11:54.746",
  "modified": "2023-04-16 15:11:55.151",
  "lastLogin": "2023-04-16 15:11:55.151",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW1lQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc2MDk3LCJleHAiOjE2ODE2Nzk2OTd9.FXOeqW9mNdIw9xYGspWaZuK2veOkK0YFNiorW5ZFUwLagdgVNA7f93WHPT2t9hX06ChGGwMJ4Bvc3iE65xLIvw",
  "active": true
}<br>

4)Probar que las rutas protegidas y la publica tengas sus correspondientes accesos dependiendo de si está autenticado o no y el rol.
4.1)Probar rutas sin autenticarse
4.1.1) Ruta GET http://localhost:8090/api/admin<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.1.2) Ruta GET http://localhost:8090/api/all<br>
Peticion:curl -X GET "http://localhost:8090/api/all" -H "accept: */*"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido Público"
}

4.1.3) Ruta GET http://localhost:8090/api/mod<br>
Peticion:curl -X GET "http://localhost:8090/api/mod" -H "accept: */*"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.1.4) Ruta GET http://localhost:8090/api/supp<br>
Peticion:curl -X GET "http://localhost:8090/api/supp" -H "accept: */*"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.1.5) Ruta GET http://localhost:8090/api/user<br>
Peticion:curl -X GET "http://localhost:8090/api/user" -H "accept: */*"<br>
Statut:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.2)Probar rutas como rol USER
De aqui en adelante se muestran peticiones y respuestas de ejemplo, pero estasno le van a funcionar tal como están, debe cambiar el token por el generado al crear el usuario o iniciar sesion.<br>
4.2.1) Ruta GET http://localhost:8090/api/admin<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3MDA5LCJleHAiOjE2ODE2ODA2MDl9.BI0R2rXwz32X-fWLSJS2BkKESyL7hk9mUp7YcF65QHRB2Daz71I-82bjdcIFnz-sbmg9t8h-t5mmxsYCDficJQ"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.2.2) Ruta GET http://localhost:8090/api/all<br>
Peticion:curl -X GET "http://localhost:8090/api/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3MDA5LCJleHAiOjE2ODE2ODA2MDl9.BI0R2rXwz32X-fWLSJS2BkKESyL7hk9mUp7YcF65QHRB2Daz71I-82bjdcIFnz-sbmg9t8h-t5mmxsYCDficJQ"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido Público"
}

4.2.3) Ruta GET http://localhost:8090/api/mod<br>
Peticion:curl -X GET "http://localhost:8090/api/mod" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3MDA5LCJleHAiOjE2ODE2ODA2MDl9.BI0R2rXwz32X-fWLSJS2BkKESyL7hk9mUp7YcF65QHRB2Daz71I-82bjdcIFnz-sbmg9t8h-t5mmxsYCDficJQ"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.2.4) Ruta GET http://localhost:8090/api/supp<br>
Peticion:curl -X GET "http://localhost:8090/api/supp" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3MDA5LCJleHAiOjE2ODE2ODA2MDl9.BI0R2rXwz32X-fWLSJS2BkKESyL7hk9mUp7YcF65QHRB2Daz71I-82bjdcIFnz-sbmg9t8h-t5mmxsYCDficJQ"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.2.5) Ruta GET http://localhost:8090/api/user<br>
Peticion:curl -X GET "http://localhost:8090/api/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3MDA5LCJleHAiOjE2ODE2ODA2MDl9.BI0R2rXwz32X-fWLSJS2BkKESyL7hk9mUp7YcF65QHRB2Daz71I-82bjdcIFnz-sbmg9t8h-t5mmxsYCDficJQ"<br>
Statut:401<br>
resultado:{
  "mensaje": "Contenido de Usuario"
}

4.3)Probar rutas como rol ADMIN<br>
4.3.1) Ruta GET http://localhost:8090/api/admin<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido de Administrador."
}

4.3.2) Ruta GET http://localhost:8090/api/all<br>
Peticion:curl -X GET "http://localhost:8090/api/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido Público"
}

4.3.3) Ruta GET http://localhost:8090/api/mod<br>
Peticion:curl -X GET "http://localhost:8090/api/mod" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.3.4) Ruta GET http://localhost:8090/api/supp<br>
Peticion:curl -X GET "http://localhost:8090/api/supp" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.3.5) Ruta GET http://localhost:8090/api/user<br>
Peticion:curl -X GET "http://localhost:8090/api/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Statut:200<br>
resultado:{
  "mensaje": "Contenido de Usuario"
}

4.4)Probar rutas como rol MODERATOR<br>
4.4.1) Ruta GET http://localhost:8090/api/admin<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTY4MTY3NzI5NCwiZXhwIjoxNjgxNjgwODk0fQ.Mey13x2_caEWEK8jPYa1l66LPbKBl-kU77Fn65UjKmoRDaRrD3R1dBcrCCnCHfrwuqJK3blz3N49_3x0hmC9Og"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.4.2) Ruta GET http://localhost:8090/api/all<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2RlcmF0b3JAdGVzdC5jb20iLCJpYXQiOjE2ODE2Nzc1NTMsImV4cCI6MTY4MTY4MTE1M30.GgrpLep5yyt8z7HaUpNq0yFwZeRyRBAIINMlT_j4gjeLuLeKBrCpGAdA6O1mcj-n6jcNKYHgstV7lrrFs_N7ZA"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido Público"
}

4.4.3) Ruta GET http://localhost:8090/api/mod<br>
Peticion:curl -X GET "http://localhost:8090/api/mod" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2RlcmF0b3JAdGVzdC5jb20iLCJpYXQiOjE2ODE2Nzc1NTMsImV4cCI6MTY4MTY4MTE1M30.GgrpLep5yyt8z7HaUpNq0yFwZeRyRBAIINMlT_j4gjeLuLeKBrCpGAdA6O1mcj-n6jcNKYHgstV7lrrFs_N7ZA"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido de Moderador."
}

4.4.4) Ruta GET http://localhost:8090/api/supp<br>
Peticion:curl -X GET "http://localhost:8090/api/supp" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2RlcmF0b3JAdGVzdC5jb20iLCJpYXQiOjE2ODE2Nzc1NTMsImV4cCI6MTY4MTY4MTE1M30.GgrpLep5yyt8z7HaUpNq0yFwZeRyRBAIINMlT_j4gjeLuLeKBrCpGAdA6O1mcj-n6jcNKYHgstV7lrrFs_N7ZA"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.4.5) Ruta GET http://localhost:8090/api/user<br>
Peticion:curl -X GET "http://localhost:8090/api/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2RlcmF0b3JAdGVzdC5jb20iLCJpYXQiOjE2ODE2Nzc1NTMsImV4cCI6MTY4MTY4MTE1M30.GgrpLep5yyt8z7HaUpNq0yFwZeRyRBAIINMlT_j4gjeLuLeKBrCpGAdA6O1mcj-n6jcNKYHgstV7lrrFs_N7ZA"<br>
Statut:200<br>
resultado:{
  "mensaje": "Contenido de Usuario"
}

4.5)Probar rutas como rol SUPERVISOR<br>
4.5.1) Ruta GET http://localhost:8090/api/admin<br>
Peticion:curl -X GET "http://localhost:8090/api/admin" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcnZpc29yQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3ODczLCJleHAiOjE2ODE2ODE0NzN9.ojMx495iZd3oKAx1gjPe0Iy1L1-T_2jr5QgvhxnPVZSHSx-lE7aUzhdi4AS8ne5J2Re3ItjX1Wvqm8RxtIR_9A"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.5.2) Ruta GET http://localhost:8090/api/all<br>
Peticion:curl -X GET "http://localhost:8090/api/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcnZpc29yQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3ODczLCJleHAiOjE2ODE2ODE0NzN9.ojMx495iZd3oKAx1gjPe0Iy1L1-T_2jr5QgvhxnPVZSHSx-lE7aUzhdi4AS8ne5J2Re3ItjX1Wvqm8RxtIR_9A"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido Público"
}

4.5.3) Ruta GET http://localhost:8090/api/mod<br>
Peticion:curl -X GET "http://localhost:8090/api/mod" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcnZpc29yQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3ODczLCJleHAiOjE2ODE2ODE0NzN9.ojMx495iZd3oKAx1gjPe0Iy1L1-T_2jr5QgvhxnPVZSHSx-lE7aUzhdi4AS8ne5J2Re3ItjX1Wvqm8RxtIR_9A"<br>
Status:401<br>
resultado:{
  "mensaje": "Acceso denegado"
}

4.5.4) Ruta GET http://localhost:8090/api/supp<br>
Peticion:curl -X GET "http://localhost:8090/api/supp" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcnZpc29yQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3ODczLCJleHAiOjE2ODE2ODE0NzN9.ojMx495iZd3oKAx1gjPe0Iy1L1-T_2jr5QgvhxnPVZSHSx-lE7aUzhdi4AS8ne5J2Re3ItjX1Wvqm8RxtIR_9A"<br>
Status:200<br>
resultado:{
  "mensaje": "Contenido de Supervisor"
}

4.5.5) Ruta GET http://localhost:8090/api/user<br>
Peticion:curl -X GET "http://localhost:8090/api/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcnZpc29yQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc3ODczLCJleHAiOjE2ODE2ODE0NzN9.ojMx495iZd3oKAx1gjPe0Iy1L1-T_2jr5QgvhxnPVZSHSx-lE7aUzhdi4AS8ne5J2Re3ItjX1Wvqm8RxtIR_9A"<br>
Statut:200<br>
resultado:{
  "mensaje": "Contenido de Usuario"
}

5)Tambien puede probar la api usando swagger, en la ruta http://localhost:8090/swagger-ui/<br>
Puede testear el inicio de sesion usando loas username y contraseñas indicadas en el numeral 3 o crear un usuario e iniciar sesion con el mismo.<br>
5.1) Para probar la creacion de usuarios vaya a la seccion Autenticacion Auth Rest Controller, ahi de click para expandir las dos opciones, inicio de sesion o creacion de usuarios.<br>
5.1.1)De click a la opcion POST /auth/signup Regsitro de usuarios.<br>
5.1.2)De click en el botón "Try it out".<br>
5.1.3)ingrese los valores en el json.<br>
5.1.4)De click en el botón "Execute".<br>
5.1.5)Revise el apartado Response Body para comprobar el resultado.<br>
5.1.6)Si se creo correctamente puede copiar el token para usarlo probando las rutas protegidas.<br>

5.2) Para probar el inicio de sesion vaya a la seccion Autenticacion Auth Rest Controller, ahi de click para expandir las dos opciones, inicio de sesion o creacion de usuarios<br>
5.2.1)De click a la opcion POST /auth/signin Inicio de sesion.<br>
5.2.2)De click en el botón "Try it out".<br>
5.2.3)Cambie el valor de username y contraseña.<br>
5.2.4)De click en el botón "Execute".<br>
5.2.5)Revise el apartado Response Body para comprobar el resultado.<br>
5.1.6)Si inició sesion correctamente puede copiar el token para usarlo probando las rutas protegidas.<br>

5.3)Probar las rutas protegidas. para probarlas vaya a la seccion Prueba de roles Api Rest Controller<br>
5.3.1)Pruebe todas las rutas sin autenticacion<br>
5.1.2)De click en la ruta que quiera probar<br>
5.2.3)De click en el botón "Try it out".<br>
5.2.4)De click en el botón "Execute". verifique el resultado.<br>

5.4)Probar las rutas con autenticacion. para probarlas vaya a la seccion Prueba de roles Api Rest Controller.<br>
Para esto necesitará un toke, el cual obtuvo al iniciar sesion o al crear un usuario.<br>
5.4.1)Copie el token obtenido al iniciar sesion o crear un usuario.<br>
5.4.2)Vaya a la parte superior y da click en el botón "Authorize".<br>
5.4.3)En la caja de texto Value escriba la palabra Bearer seguida de un espacio y el valor del token, por ejemplo <br>
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW1lQHRlc3QuY29tIiwiaWF0IjoxNjgxNjc1OTE1LCJleHAiOjE2ODE2Nzk1MTV9.fop32GgvSGADp6xH4XgnHxuEH8-Gv_JF1mH-IcP_yXgue8JN0PHkCvmYdE2e2o2x1KG6rKPQ6WsniW6DR0eJyw<br>
NOTA: Recuerde que este token no le servirá porque en el momento de usarlo ya estará expirado.<br>
5.4.4)Vaya a la ruta que desee probar y e da click.<br>
5.4.5)De click en el botón "Try it out".<br>
5.4.5)Observe el apartado Response Body y verifique el resultado.<br>
5.4.6)Puede repetir los mismo pasos con todas las rutas y con todos los usuarios que desee.<br>
