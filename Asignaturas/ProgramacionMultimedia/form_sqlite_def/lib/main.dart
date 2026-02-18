import 'package:flutter/material.dart';

import 'package:form_sqlite_def/screens/Formulario.dart';

import 'package:form_sqlite_def/screens/PantallaDatos.dart';







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

          return MaterialPageRoute(builder: (context) => const Formulario());

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