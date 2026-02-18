import 'package:flutter/material.dart';

import '../bbdd/DataBaseSqlite.dart';
import '../bbdd/FormularioData.dart';

class PantallaDatos extends StatelessWidget {
  final Map<String, dynamic> datos;
  final DataBaseSqlite _db = DataBaseSqlite();

  PantallaDatos({super.key, required this.datos});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Registros en SQLite'),
      ),
      body: FutureBuilder<List<FormularioData>>(

        // Llamamos al método getEnvios
        future: _db.getEnvios(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }

          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No hay datos en la base de datos.'));
          }

          // Obtenemos la lista completa
          final listaEnvios = snapshot.data!;

          return ListView.builder(
            itemCount: listaEnvios.length,
            itemBuilder: (context, index) {
              final item = listaEnvios[index];
              return Card(
                  margin: const EdgeInsets.all(8.0),
                  child: ListTile(
                    title: Text(item.nombre),
                    subtitle: Text('Tel: ${item.telefono} | Gusto: ${item.flutterSliderValue}'),
                  ));
              },
          );
        },
      ),
    );
  }
}