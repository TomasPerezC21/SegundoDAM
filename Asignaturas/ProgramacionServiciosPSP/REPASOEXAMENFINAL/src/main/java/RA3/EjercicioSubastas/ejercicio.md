# 🏆 Reto de Programación: Sistema de Subastas "AuctionMaster"

Este ejercicio aplica los conceptos de **sistemas distribuidos** donde los procesos se ejecutan en máquinas independientes y se coordinan mediante **protocolos de comunicación**. Utilizaremos el modelo **Cliente/Servidor** y la potencia de los **hilos (multithreading)** para gestionar múltiples usuarios simultáneamente.

---

## 📝 Escenario
Debéis construir un sistema donde un **Servidor** central gestiona la subasta de un artículo único. Los **Clientes** se conectan por red para pujar. El servidor debe garantizar la integridad de los datos (que no se pierdan pujas) y atender a todos de forma fluida.

---

## 1. El Servidor (TCP Multihilo)
El servidor debe "delegar" el trabajo para no colapsar[cite: 14, 262].
    * **Socket de Escucha**: Debe usar un `ServerSocket` en el puerto `5555`.
* **Estado de la Subasta**: Mantendrá una variable `precioActual` (inicia en 100) y `ganadorActual`.
* **Concurrencia**: Por cada cliente que se conecte mediante `accept()`, el servidor debe lanzar un nuevo **hilo (Worker)**.
* **Sección Crítica (Lógica)**: 
    * Al recibir una puja, se debe usar un bloque **sincronizado** (`synchronized`) para evitar que dos hilos actualicen el precio al mismo tiempo.
    * **Protocolo**: Si la puja recibida es mayor al precio actual, se actualiza y se responde `ACEPTADA`. Si no, se responde `RECHAZADA`.
* **Persistencia**: El servidor nunca debe cerrarse automáticamente, quedando siempre a la espera de nuevos clientes.

## 2. El Cliente (TCP)
El cliente representa al postor que se conecta a la red.
* **Conexión**: Debe crear un `Socket` apuntando a la IP del servidor y al puerto `5555`.
* **Comunicación**: Utilizará flujos de datos (`Streams`) como `BufferedReader` para leer y `PrintWriter` para enviar texto.
* **Flujo de Usuario**:
    1.  Lee el estado actual de la subasta al conectar.
    2.  Pide por teclado el nombre y la cantidad a pujar.
    3.  Envía la puja y muestra la respuesta del servidor.
    4.  Si el usuario escribe `FIN`, el cliente cierra sus recursos y finaliza.

## 3. Requisitos Técnicos
* **Protocolo de Transporte**: Se debe usar **TCP** para garantizar que las pujas lleguen completas y en orden.
* **Identificación**: El servidor debe asignar un `idCliente` único a cada conexión para loguear la actividad en consola.
* **Cierre de Recursos**: Es obligatorio cerrar sockets y streams en bloques `finally` o asegurar su cierre manual para evitar fugas de memoria.

---

## 🔥 Reto Extra (Modo Pro)
Implementad un **límite de tiempo**. Si en 20 segundos nadie supera la puja actual, el servidor anuncia al ganador y resetea la subasta. Para esto, podéis investigar el método `setSoTimeout()` en el socket.