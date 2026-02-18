import 'package:flutter/material.dart';

class PantallaDatos extends StatelessWidget {
  final Map<String, dynamic> datos;


  const PantallaDatos({super.key, required this.datos});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Resumen del Formulario'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Nombre: ${datos['nombre']}'),
            Text('Teléfono: ${datos['telefono']}'),
            Text('¿Te gustaria trabajar en Flutter? ${datos['trabajar']}'),
            Text('Gustosidad de Flutter: ${datos['gustosidad']}'),
          ],
        ),
      ),
    );
  }
}