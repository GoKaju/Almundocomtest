# Almundocomtest
Prueba java para Almundo.com

## Consigna
Existe un call center donde hay 3 tipos de empleados: operador,
supervisor y director. El proceso de la atención de una llamada
telefónica en primera instancia debe ser atendida por un operador, si
no hay ninguno libre debe ser atendida por un supervisor, y de no
haber tampoco supervisores libres debe ser atendida por un director.

## Requerimientos

Diseñar el modelado de clases y diagramas UML necesarios
para documentar y comunicar el diseño.

> * Anexo se encuentran en la carpeta docs.

Debe existir una clase Dispatcher encargada de manejar las llamadas, y debe contener el método dispatchCall para que las
asigne a los empleados disponibles.

> * Se creo la clase y el metodo, el cual recibe la llamada, la 
envia a una cola e invoca el metodo assingCall el cual valida la
disponibilidad  de llamadas y empleados y las asinga segun la 
jerarquia mencionada.

La clase Dispatcher debe tener la capacidad de poder procesar
10 llamadas al mismo tiempo (de modo concurrente).

> * Se utiliza la clase ExecutorService, la cual se encarga de 
gestionar los procesos en modo concurrende.

Cada llamada puede durar un tiempo aleatorio entre 5 y 10
segundos.

> * Al asignar la llamada al empleado se calcula una duracion 
aleatoria.

Debe tener un test unitario donde lleguen 10 llamadas.

> * Test en el proyecto.

##  Extras/Plus

Dar alguna solución sobre qué pasa con una llamada cuando no
hay ningún empleado libre.

> * Todas la llamadas se envian a una cola, y de ahi se van tomando para su procesamiento, 
al no haber empleados disponibles estas quedan en espera en la cola.

Dar alguna solución sobre qué pasa con una llamada cuando
entran más de 10 llamadas concurrentes.

> * La clase ExecutorService posee una cola interna para manejar los procesos que superan el 
pool de threads, entonces esas llamadas con empleado ya asignado quedan en espera hasta que se libere un thread.

Agregar los tests unitarios que se crean convenientes.
Agregar documentación de código.

> *  Java doc.
