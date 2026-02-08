import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class DatabaseHelper {
  static final DatabaseHelper _instance = DatabaseHelper._internal();
  static Database? _database;

  factory DatabaseHelper() => _instance;
  DatabaseHelper._internal();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    String path = join(await getDatabasesPath(), 'formulario.db');
    return await openDatabase(
      path,
      version: 1,
      onCreate: (db, version) {
        return db.execute(
          'CREATE TABLE registros(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, trabajar INTEGER, gustosidad REAL)',
        );
      },
    );
  }

  // Guardar datos
  Future<int> insertar(Map<String, dynamic> row) async {
    Database db = await database;
    return await db.insert('registros', row);
  }

  // Recuperar el último registro
  Future<Map<String, dynamic>?> getUltimoRegistro() async {
    Database db = await database;
    List<Map<String, dynamic>> res = await db.query('registros', orderBy: 'id DESC', limit: 1);
    return res.isNotEmpty ? res.first : null;
  }

  // Añade esto dentro de tu clase DatabaseHelper
  Future<List<Map<String, dynamic>>> getTodosLosRegistros() async {
    Database db = await database;
    // Consultamos todos los registros ordenados por el más reciente
    return await db.query('registros', orderBy: 'id DESC');
  }

}