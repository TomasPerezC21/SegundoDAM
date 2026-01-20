import 'package:flutter/material.dart';

class CircleAvatarPage extends StatelessWidget {

  final _usuarios = {
    'Maria Antonia' : 'https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
    'Ana Isabel' : 'https://images.pexels.com/photos/1264210/pexels-photo-1264210.jpeg?auto=compress&cs=tinysrgb&w=600',
    'Miguel Poveda' : 'https://images.pexels.com/photos/1704488/pexels-photo-1704488.jpeg?auto=compress&cs=tinysrgb&w=600',
    'Jhon Smith' : 'https://images.pexels.com/photos/1043471/pexels-photo-1043471.jpeg?auto=compress&cs=tinysrgb&w=600'
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Ejemplo de Circle Avatar'),
        ),
        body: _lista()
    );
  }

  Widget _lista(){
    return ListView(
        children: _elementosLista()
    );
  }
  List<Widget> _elementosLista(){
    List<Widget> lista = [];
    for (MapEntry<String,String> dato in _usuarios.entries) {
      lista..add(SizedBox(height: 5.0,))
        ..add(_circleAvatarLista1(dato));
    };

    return lista;

  }

  Widget _circleAvatarLista1(MapEntry dato){
    return Ink(
        color: Colors.grey,
        child:Container(
          height: 70.0, // Establece la altura deseada,
          child: ListTile(
            title: Text(dato.key),
            trailing: CircleAvatar(
              backgroundColor: Colors.blue,
              backgroundImage: NetworkImage(dato.value),
              radius: 25.0,
            ),
            onTap: () {},
          ),
        )
    );
  }
}