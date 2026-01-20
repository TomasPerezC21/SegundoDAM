import 'package:asgfasgasgasgasgasgasgasg/screens/BotonesPage.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/CardPage.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/CircleAvatarPage.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/ImagenesPage.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/ListasPage.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/MiWidget.dart';
import 'package:asgfasgasgasgasgasgasgasg/screens/TextosPage.dart';
import 'package:flutter/material.dart';

void main() => runApp(MiMaterialApp());

class MiMaterialApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      home: CardsPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}


