import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class IniciacionStateful extends StatefulWidget {
  const IniciacionStateful({super.key});
  @override
  _IniciacionStatefulState createState() => _IniciacionStatefulState();
}
class _IniciacionStatefulState extends State<IniciacionStateful> {
  int _contador = 0;

  void _incrementoContador() {
    setState(() {
      _contador++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Inicio Widget con estado'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'has pulsado:',
            ),
            Text(
                '$_contador'),
            TextButton(
                onPressed: _incrementoContador,
                child: Text('Clickme'),
                style: TextButton.styleFrom(backgroundColor: Colors.blue)
            )
          ],
        ),
      ),
    );
  }
}