import 'package:asgfasgasgasgasgasgasgasg/rutas.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/Formularios.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/navegacion.dart';
import 'package:flutter/material.dart';
void main() => runApp(MyApp());
class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Calculadora DAM',
      theme: ThemeData(useMaterial3: false),
     // routes: obtenerRutas(),
      home: FormularioPage(),
    );
  }
}


