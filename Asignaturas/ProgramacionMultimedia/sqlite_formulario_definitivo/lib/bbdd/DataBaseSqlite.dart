import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'FormularioData.dart';

class DataBaseSqlite {
  Future<Database> initializedDB() async {
    String path = await getDatabasesPath();
    return openDatabase(
      join(path, 'formulario.db'),
      version: 1,
      onCreate: (Database db, int version) async {
        await db.execute('''
        CREATE TABLE form_data (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT,
        telefono TEXT,
        likesFlutter INTEGER,
        flutterSliderValue REAL
        )
                ''');
      },
    );
  }

// insert
  Future<int> insertEnvio(FormularioData dato) async {
    int result = 0;
    final Database db = await initializedDB();
    result = await db.insert('form_data', dato.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

// get
  Future<List<FormularioData>> getEnvios() async {
    final Database db = await initializedDB();
    final List<Map<String, Object?>> queryResult = await db.query('form_data');
    return queryResult.map((e) => FormularioData.fromMap(e)).toList();
  }
// delete
  Future<void> deleteEnvio(int id) async {
    final db = await initializedDB();
    await db.delete(
      'form_data',
      where: "id = ?",
      whereArgs: [id],
    );

  }

}