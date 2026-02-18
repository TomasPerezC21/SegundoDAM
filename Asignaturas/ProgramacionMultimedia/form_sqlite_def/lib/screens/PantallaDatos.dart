import 'package:flutter/material.dart';

class PantallaDatos extends StatelessWidget {
  final Map<String, dynamic> datos;

  const PantallaDatos({super.key, required this.datos});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Resumen Guardado')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Nombre: ${datos['nombre']}'),
            Text('Teléfono: ${datos['telefono']}'),
            Text('Likes Flutter: ${datos['likesFlutter'] == 1 ? "Sí" : "No"}'),
            Text('Puntuación: ${datos['flutterSliderValue']}'),
            const SizedBox(height: 20),
            const Text('¡Datos guardados correctamente en SQLite!',
                style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}