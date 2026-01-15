import 'package:flutter/material.dart';
class BotonesPage extends StatelessWidget {
  const BotonesPage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('botones'),
        backgroundColor: Colors.red,
        centerTitle: true,
      ),
      body: Row(
          children: [
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.green,
                foregroundColor: Colors.white,
              ),
              onPressed: () {
// Acción al presionar el botón
                print('Presionaste el ElevatedButton');
              },
              child: Text('Presioname'),),
          ]
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          print('Presionamos el botón ADD');
        },
        backgroundColor: Colors.red,
        tooltip: 'Presiona para hacer algo',
        child: Icon(Icons.add),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
    );
  }
}