import 'package:firebase_auth/firebase_auth.dart';


class AuthService {
  final FirebaseAuth _auth = FirebaseAuth.instance;


  Stream<User?> get usuarioStream => _auth.authStateChanges();


  User? get usuarioActual => _auth.currentUser;


  Future<String?> registrar(String email, String password) async {
    try {
      await _auth.createUserWithEmailAndPassword(
          email: email,
          password: password
      );
      return "SUCCESS";
    } on FirebaseAuthException catch (e) {
      return _manejarErrores(e);
    } catch (e) {
      return "Ocurrió un error inesperado";
    }
  }


  Future<String?> login(String email, String password) async {
    try {
      await _auth.signInWithEmailAndPassword(
          email: email,
          password: password
      );
      return "SUCCESS";
    } on FirebaseAuthException catch (e) {
      return _manejarErrores(e);
    } catch (e) {
      return "Ocurrió un error inesperado";
    }
  }


  Future<void> cerrarSesion() async {
    await _auth.signOut();
  }


  String _manejarErrores(FirebaseAuthException e) {
    switch (e.code) {
      case 'invalid-email':
        return "El formato del correo electrónico es inválido.";
      case 'user-disabled':
        return "Este usuario ha sido deshabilitado.";
      case 'user-not-found':
        return "No existe ningún usuario con este correo.";
      case 'wrong-password':
        return "La contraseña es incorrecta.";
      case 'email-already-in-use':
        return "El correo ya está registrado por otra cuenta.";
      case 'weak-password':
        return "La contraseña es demasiado débil.";
      case 'operation-not-allowed':
        return "El inicio de sesión con email no está habilitado en Firebase.";
      default:
        return "Error: ${e.message}";
    }
  }
}
