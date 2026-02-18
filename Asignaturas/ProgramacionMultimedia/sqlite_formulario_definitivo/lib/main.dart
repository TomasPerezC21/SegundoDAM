import 'package:flutter/material.dart';
import 'screens/Formulario.dart';
import 'screens/PantallaDatos.dart';


void main() => runApp(const MiAplicacion());

class MiAplicacion extends StatelessWidget {
  const MiAplicacion({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Ejercicio Formulario',
      initialRoute: '/',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: false
      ),
      onGenerateRoute: (settings) {
        if (settings.name == '/') {
          return MaterialPageRoute(builder: (context) => Formulario());
        }

        if (settings.name == '/enviado') {
          final args = settings.arguments as Map<String, dynamic>;
          return MaterialPageRoute(
            builder: (context) => PantallaDatos(datos: args),
          );
        }
        return null;
      },
    );
  }
}