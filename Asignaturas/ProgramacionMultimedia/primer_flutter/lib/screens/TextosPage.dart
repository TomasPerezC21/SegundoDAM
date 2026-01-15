import 'package:flutter/material.dart';
class TextosPage extends StatelessWidget {
  const TextosPage({Key? key}) : super(key: key);
  TextStyle get estiloTexto {
    return const TextStyle(
      fontSize: 35.3,
      backgroundColor: Colors.amber,
      color: Colors.black,
      fontWeight: FontWeight.bold,
    );
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Textos con diferentes estilos'),
        backgroundColor: Colors.red,
        centerTitle: true,
      ),
      body: DefaultTextStyle(
          style: estiloTexto,
          child:RichText(
            text: const TextSpan(text: 'Este es un texto TextSpan',style: TextStyle(color: Colors.green),
                children: [
                  TextSpan(text: ' con un estilo diferente', style: TextStyle(color:Colors.brown)),
                  TextSpan(text: ' en cada parte del texto.', style: TextStyle(color:Colors.yellow)),
                  TextSpan(text: ' Si no se pone nada, hereda el estilo del TextSpan que lo contenga'),
                ]),
          )
      ),
    );
  }
}