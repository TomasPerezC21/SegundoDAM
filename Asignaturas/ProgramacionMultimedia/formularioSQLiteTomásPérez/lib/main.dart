import 'package:flutter/material.dart';
import 'screens/formulario.dart';
import 'screens/PantallaDatos.dart';

void main() => runApp(const MiAplicacion());

class MiAplicacion extends StatelessWidget {
  const MiAplicacion({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Ejercicio Formulario SQLite',
      debugShowCheckedModeBanner: false,
      initialRoute: '/',
      theme: ThemeData(
          primarySwatch: Colors.blue,
          useMaterial3: false
      ),
      routes: {
        '/': (context) => const Formulario(),
        '/enviado': (context) => const PantallaDatos(),
      },
    );
  }
}