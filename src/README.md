# Resumen

Primero hemos **creado la Base de Datos** y hemos hecho una réplica para hacer pruebas de código, **comprobar el funcionamiento** de este y los datos introducidos.

Hemos empezado por **inserir los datos** de las tablas en el orden estipulado en el documento, creando sus respectivas funciones para **leer el archivo**, **inserir los
datos** del archivo en la Base de Datos, y hacer los **Selects** necesaros de las tablas ya inseridas.

Mientras ibamos creando los códigos para **inserir los datos** en las tablas, ibamos subiendo el código resultante a los repositorios de **GITHUB** para poder acceder
todos a él fácilmente cuándo lo necesitáramos.

Hemos tenido algunos **problemas** con ciertos campos, como pasar por alto los valores que daban 99 al representar el Total Nacional,
algunos campos que eran todo 0 dejarlos como NULL, etc.

Mientras tanto, otros miembros del grupo iban creando las **sentencias SQL** y preparando para hacer comrpobaciones al acabar de inserir todos los datos.

# Explicación de las tablas en profundidad

Antes de entrar a expxlicar tabla por tabla, nos hemos dado cuenta que para seleccionar los datos necesarios, hay que restarle 1 al rango inicial de este
para así hacernos con los datos exactos.

## Comunitats Autónomes

En esta tabla, ha sido algo difícil aún siendo la más simple porque es la que más pruebas se ha llevado.

Al empezar desde 0 con un temario nuevo, y no saber nada del tema, hemos tenido que hacer prueba tras prueba para aprender a utilizar bien el código proporcionado.

Una vez comprovado y optimizado, hemos creado el primer método funcional para insertar la información recogida del documento correspondiente en la tabla adecuada
de nuestra base de datos.

En esta tabla, hemos tenido que dejar de lado cierta información al no necesitar el Total Nacional (99).

## Provincies

Al realizar esta tabla, hemos tenido que crear métodos extra y extraer información adicional para poder llevar a cabo los SELECT necessarios que nos proporcionarían
los datos de la clabe foranea requerida en la tabla.

## Municipis

Esta tabla nos daba errores con los duplicados así que decidimos agregar un campo más a los únicos y así poder evitar duplicados.

Aún y habiendo hecho esto, no hemos conseguido deshacernos de todos los duplicados.

## Eleccions

Tabla poco complicada, ya que la única fila que se ha añadido ha sido a mano, desde el Workbench, ya que era inventada simplemente para darle un "Título" a las
elecciones.

## Eleccions Municipals

Esta tabla hemos tenido que hacer diferentes cálculos para conseguir el total de votos emitidos y el total de votos válidos ya que estos datos se obtienen con la
suma de los otros campos especificados.

## Candidatures

Tabla sencilla pero a la vez enrebesada al tener tantos campos con nombres tan parecidos.

Simplemente los errores obtenidos han sido arreglados fácilmente al prestar más atención al código y a los campos.

## Persones

Tabla sencilla con el único problema de que hemos tenido que introducir todas las fechas de nacimiento como NULL al no tener datos sobre estas. (En el documento de 
datos, todas las fechas de nacimiento son 0000-00-00)

## Candidats

Tabla muy extensa, en la que hemos necesitado de 3 SELECT diferentes para poder aportar las claves foraneas requeridas.

En ddefinitiva un insert muy lento al necesitar tantos datos y tantos SELECT.

## Vots municipals

Tabla que aún introduciendo bien los datos, no conseguimos quitarnos de encima un par de errores de duplicado.

## Vots comunitats autonomes

Tabla con el mismo error que vots municipals, que no conseguimmos deshacernos de los duplicados.

## Vots provincies

Tabla que aún siendo tan enrebesada como las anteriores de Votos al no tener ninguna de estas, una clave primaria personal, sino con claves foraneas, no ha 
obtenido duplicado de datos.

