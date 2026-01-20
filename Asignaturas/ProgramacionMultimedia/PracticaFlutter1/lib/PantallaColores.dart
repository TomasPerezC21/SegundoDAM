import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: PantallaColores(),
    );
  }
}

class PantallaColores extends StatelessWidget {
  const PantallaColores({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          Expanded(child: Container(color: Colors.red)),
          Expanded(child: Container(color: Colors.blue)),
          Expanded(child: Container(color: Colors.green)),
          Expanded(child: Container(color: Colors.yellow)),

          Expanded(
            child: Column(
              children: [
                Expanded(child: Container(color: Colors.red)),
                Expanded(child: Container(color: Colors.blue)),
                Expanded(child: Container(color: Colors.green)),
                Expanded(child: Container(color: Colors.yellowAccent)),

              Expanded(
                child: Row(
                  children: [
                    Expanded(child: Container(color: Colors.red)),
                    Expanded(child: Container(color: Colors.blue)),
                    Expanded(child: Container(color: Colors.green)),
                    Expanded(child: Container(color: Colors.yellow)),
                    Expanded(child: Container(color: Colors.orange))
                  ],
                )
              )
              ],
            ),
          ),
        ],
      ),
    );
  }
}
