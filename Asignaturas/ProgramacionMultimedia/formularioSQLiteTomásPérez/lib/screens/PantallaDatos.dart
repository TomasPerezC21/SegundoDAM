import 'package:flutter/material.dart';
import 'package:formulario_flutter/bbdd/db_helper.dart';

class PantallaDatos extends StatefulWidget {
  const PantallaDatos({super.key});

  @override
  State<PantallaDatos> createState() => _PantallaDatosState();
}

class _PantallaDatosState extends State<PantallaDatos> {

  List<Map<String, dynamic>> listaUsuarios = [];

  @override
  void initState() {
    super.initState();
    cargarDesdeBBDD();
  }

  void cargarDesdeBBDD() async {

    var datos = await DatabaseHelper().getTodosLosRegistros();
    setState(() {
      listaUsuarios = datos;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Lista de Usuarios SQLite'),
      ),

      body: listaUsuarios.isEmpty
          ? const Center(child: Text('No hay datos todavía'))
          : ListView.builder(
        itemCount: listaUsuarios.length,
        itemBuilder: (context, index) {

          var usuario = listaUsuarios[index];

          return ListTile(
            title: Text('Nombre: ${usuario['nombre']}'),
            subtitle: Text('Tel: ${usuario['telefono']} - Gusto: ${usuario['gustosidad']}'),
            // Icono simple según el valor 1 o 0
            trailing: Icon(
                usuario['trabajar'] == 1 ? Icons.check : Icons.close
            ),
          );
        },
      ),
    );
  }
}