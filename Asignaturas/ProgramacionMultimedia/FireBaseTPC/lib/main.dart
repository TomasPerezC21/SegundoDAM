import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_database/ui/firebase_animated_list.dart';

import 'PushNotificationService.dart';


void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  await PushNotificationService.inicializarApp();
  runApp(const MyApp());
}


class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Manual Firebase Auth',
        theme: ThemeData(primarySwatch: Colors.blue, useMaterial3: true),
        home:ChatPage()
    );
  }
}


class ChatPage extends StatefulWidget {
  const ChatPage({super.key});


  @override
  State<ChatPage> createState() => _ChatPageState();
}
class _ChatPageState extends State<ChatPage> {


  final DatabaseReference _mensajesRef = FirebaseDatabase.instanceFor(
    app: Firebase.app(),
    databaseURL: 'https://fir-tpc-default-rtdb.europe-west1.firebasedatabase.app/',
  ).ref('mensajes');


  final TextEditingController _nombreController = TextEditingController();
  final TextEditingController _mensajeController = TextEditingController();


  void _enviarMensaje() {
    if (_nombreController.text.isEmpty || _mensajeController.text.isEmpty) return;


    _mensajesRef.push().set({
      'autor': _nombreController.text,
      'contenido': _mensajeController.text,
      'hora': ServerValue.timestamp,
    });


    _mensajeController.clear();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Chat Público')),
      body: Column(
        children: [
          Expanded(
            child: FirebaseAnimatedList(
              query: _mensajesRef,
              itemBuilder: (context, snapshot, animation, index) {
                final json = snapshot.value as Map<dynamic, dynamic>;
                return FadeTransition(
                  opacity: animation,
                  child: ListTile(
                    title: Text(json['autor'], style: const TextStyle(fontWeight: FontWeight.bold)),
                    subtitle: Text(json['contenido']),
                  ),
                );
              },
            ),
          ),


          Container(
            padding: const EdgeInsets.all(15),
            color: Colors.grey[100],
            child: Column(
              children: [
                TextField(
                  controller: _nombreController,
                  decoration: const InputDecoration(labelText: 'Tu Nombre', icon: Icon(Icons.person)),
                ),
                Row(
                  children: [
                    Expanded(
                      child: TextField(
                        controller: _mensajeController,
                        decoration: const InputDecoration(labelText: 'Escribe un mensaje...', icon: Icon(Icons.chat)),
                      ),
                    ),
                    IconButton(
                      icon: const Icon(Icons.send, color: Colors.blue),
                      onPressed: _enviarMensaje,
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
