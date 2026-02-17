import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'AuthService.dart';

class AuthScreen extends StatefulWidget {
  const AuthScreen({super.key});


  @override
  State<AuthScreen> createState() => _AuthScreenState();
}


class _AuthScreenState extends State<AuthScreen> {
  bool esLogin = true;
  final _emailController = TextEditingController();
  final _passController = TextEditingController();
  final _authService = AuthService();


  void _enviar() async {
    String email = _emailController.text.trim();
    String password = _passController.text.trim();
    String? resultado;


    if (esLogin) {
      resultado = await _authService.login(email, password);
    } else {
      resultado = await _authService.registrar(email, password);
    }


    if (resultado != "SUCCESS" && mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text(resultado!), backgroundColor: Colors.red),
      );
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(esLogin ? 'Iniciar Sesión' : 'Crear Cuenta')),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(controller: _emailController, decoration: const InputDecoration(labelText: 'Email')),
            TextField(controller: _passController, decoration: const InputDecoration(labelText: 'Contraseña'), obscureText: true),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _enviar, child: Text(esLogin ? 'Entrar' : 'Registrar')),
            TextButton(
              onPressed: () => setState(() => esLogin = !esLogin),
              child: Text(esLogin ? '¿No tienes cuenta? Regístrate' : '¿Ya tienes cuenta? Entra'),
            ),
          ],
        ),
      ),
    );
  }
}
