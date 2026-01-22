import 'package:flutter/material.dart';


class navigacionHome extends StatefulWidget {
  const navigacionHome({Key? key}) : super(key: key);


  @override
  _PrincipalNavegacionPageState createState() =>
      _PrincipalNavegacionPageState();
}


class _PrincipalNavegacionPageState extends State<navigacionHome> {
  int _index = 0;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('App con navegación'),
      ),
      body: _createPantallas(),
    );
  }




  Widget _createPantallas() {
    return Container(
      width: double.infinity,
      // Esto hará que el Container ocupe todo el ancho disponible
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text("Pantalla principal"),
          TextButton(
            child: Text("ir a parte 1"),
            onPressed: () {
              Navigator.pushNamed(context, '/parte1');
            },
          )
        ],
      ),
    );
  }
}


class Tab1 extends StatelessWidget {
  Tab1(BuildContext context);


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
          onPressed: (){
          },
          child: Icon(Icons.arrow_back)
      ),
      appBar: AppBar(
        title: Text('Parte 1'),
      ),
      body: Image.network(
          "https://covers.openlibrary.org/b/isbn/9786070712722-L.jpg"),
    );
  }
}


