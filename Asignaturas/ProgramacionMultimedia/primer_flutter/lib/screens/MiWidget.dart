import 'package:flutter/material.dart';
class MiWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Título del AppBar'),
        backgroundColor: Colors.red,
        centerTitle: true,
      ),
      body: const Center(
        child: Text('Cuerpo del body'),
      ),
    );
  }
}