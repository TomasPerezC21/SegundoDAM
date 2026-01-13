import 'package:flutter/material.dart';

void main() => runApp(MiMaterialApp());

class MiMaterialApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      home: MiWidget(),
      debugShowCheckedModeBanner: false,
    );
  }
}


class MiWidget extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: Drawer(
        // Contenido del drawer
          child: ListView(
              padding: EdgeInsets.zero,
              children: [
                ListTile(
                  title: Text('Opción 1'),
                  onTap: () {
                    // Acción opción 1  drawer
                    Navigator.pop(context); // Cierra el drawer
                  },
                ),
                ListTile(
                  title: Text('Opción 2'),
                  onTap: () {
                    // Acción opción 2 drawer
                    Navigator.pop(context); // Cierra el drawer
                  },
                ),
              ]
          )
      ),

      appBar: AppBar(
        title: const Text(' Mi Título del AppBar'),
        actions: [
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              // Acción de búsqueda
            },
          ),
        ],
        backgroundColor: Colors.red,
        centerTitle: true,
      ),
      body: const Center(
        child: Text('Cuerpo del body'),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Acción al hacer click
        },
        child: const Icon(Icons.add),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Inicio',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.settings),
            label: 'Configuración',
          ),
        ],
      ),

    );
  }

}