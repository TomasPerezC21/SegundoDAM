import 'package:flutter/material.dart';

class CardsPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Ejemplo de uso de Cards'),
        ),
        body: ListView(
          children: <Widget>[
            _crearTarjeta1(),
            Divider(),
            _crearTarjeta2(),
            Divider(),
            _crearTarjeta3(),
            Divider(),
          ],
        )
    );
  }

  Widget _crearTarjeta1() {
    return Card(
      clipBehavior: Clip.hardEdge,   //recorta la imagen no salga por fuera del border redondeado
      elevation: 8.0,
      shape: const RoundedRectangleBorder(
          side: BorderSide(
              width: 2.0, color: Colors.black, style: BorderStyle.solid),
          borderRadius: BorderRadius.all(Radius.circular(30.0))
      ),
      child: Column(
        children: [
          Center(
              child: Image.network('https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Google-flutter-logo.svg/512px-Google-flutter-logo.svg.png')
          ),
          Padding(
            padding: const EdgeInsets.all(25.0),
            child: Text('Ejemplo de texto asociado a una tarjeta'),
          )
        ],
      ),
    );
  }

  Widget _crearTarjeta2() {
    return const Card(
      color: Colors.black,
      elevation: 5.0,
      child: ListTile(
        leading: Icon (Icons.account_circle_rounded),
        title: Text('Ejemplo de texto',
          style: TextStyle(color: Colors.red,
              fontWeight: FontWeight.bold,
              fontSize: 20.2),
        ),
        trailing: Icon(Icons.add),
      ),
    );
  }

  Widget _crearTarjeta3(){
    return const Card(
      shape: const CircleBorder(
        side: BorderSide(width: 2.0, color: Colors.black, style: BorderStyle.solid),
      ),
      color: Colors.blue,
      elevation: 10.0,
      child: Icon (Icons.abc, size: 120.0,),
    );
  }

}