import 'package:flutter/material.dart';
class ImagenesPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
// TODO: implement build
    return Scaffold(
        appBar: AppBar(
          title: Text('Ejemplos de imágenes'),
        ),
        body: Column(
          children: <Widget>[
            FadeInImage.assetNetwork(
              placeholder: 'assets/imagenes/revista_hola_logo.png',
              image: 'https://upload.wikimedia.org/wikipedia/commons/2/23/Google_2013-2015.jpg',
              fadeInDuration: Duration(seconds: 1),
              height: 189.0,
              width: 393.0,
            ),
          ],
        )
    );
  }
}