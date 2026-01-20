import 'package:flutter/material.dart';

class ListasPage extends StatefulWidget {
  @override
  _ListasPageState createState() => _ListasPageState();
}

class _ListasPageState extends State<ListasPage> {
  List<String> _datos = [
    'Elemento 1', 'Elemento 2', 'Elemento 3', 'Elemento 4', 'Elemento 5', 'Elemento 6',
    'Elemento 7', 'Elemento 8', 'Elemento 9', 'Elemento 10', 'Elemento 11', 'Elemento 12',
    'Elemento 13', 'Elemento 14', 'Elemento 15', 'Elemento 16', 'Elemento 17', 'Elemento 18',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('ListView.builder'),
      ),
      body: _cargarListaBuilder(),
    );
  }

  Widget _cargarListaBuilder() {
    return ListView.builder(
      itemCount: _datos.length,
      itemBuilder: (context, posicion) {
        final item = _datos[posicion];

        return Dismissible(
          key: Key(item),
          direction: DismissDirection.horizontal,
          confirmDismiss: (direction) async {
            if (direction == DismissDirection.startToEnd) {
              return true;
            }
            return false;
          },
          onDismissed: (direction) {
            if (direction == DismissDirection.startToEnd) {
              setState(() {
                _datos.removeAt(posicion);
              });

              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(
                  content: Text('$item eliminado'),
                ),
              );
            }else{
              print("no hago nada");
            }
          },
          background: Container(
            color: Colors.red,
            padding: EdgeInsets.only(left: 20),
            alignment: Alignment.centerLeft,
            child: Icon(Icons.delete, color: Colors.white),
          ),
          secondaryBackground: Container(
            color: Colors.green,
            padding: EdgeInsets.only(right: 20),
            alignment: Alignment.centerRight,
            child: Icon(Icons.archive, color: Colors.white),
          ),
          child: _elementoLista(item),
        );
      },
    );
  }

  Widget _elementoLista(String elemLista) {
    return ListTile(
      title: Text(elemLista),
      subtitle: Text('Subtítulo de $elemLista'),
      leading: Icon(Icons.accessibility),
      trailing: Icon(Icons.more_vert),
      onTap: () {},
    );
  }
}