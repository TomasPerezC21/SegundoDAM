import 'package:flutter/material.dart';
class ListasPage extends StatelessWidget{
  final _datos = ['Elemento 1','Elemento 2','Elemento 3','Elemento 4','Elemento 5','Elemento 6',
    'Elemento 7','Elemento 8','Elemento 9','Elemento 10','Elemento 11','Elemento 12',
    'Elemento 13','Elemento 14','Elemento 15','Elemento 16','Elemento 17','Elemento 18',];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('ListView.builder'),
        ),
        body: _cargarListaBuilder()
    );
  }
  Widget _cargarListaBuilder() {
    return ListView.builder(
        itemCount: _datos.length,
        itemBuilder: (buildcontext, posicion){
          print (posicion+1);
          return _elementoLista(posicion);
        }
    );
  }
  Widget _elementoLista(pos) {
    var elemLista = _datos.elementAt(pos);
    return ListTile(
      title: Text(elemLista),
      subtitle: Text('Subtítulo de $elemLista'),
      leading: Icon(Icons.accessibility),
      trailing: Icon(Icons.more_vert),
      onTap: () {},
    );
  }
}