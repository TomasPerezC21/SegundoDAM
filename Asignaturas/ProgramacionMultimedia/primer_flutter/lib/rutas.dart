
import 'package:asgfasgasgasgasgasgasgasg/screens/navegacion.dart';
import 'package:flutter/material.dart';

Map<String,WidgetBuilder>obtenerRutas(){

  return <String,WidgetBuilder>{
    '/home' : (BuildContext context) => navigacionHome(),
    '/parte1' : (BuildContext context) => Tab1(context),
  };


}
