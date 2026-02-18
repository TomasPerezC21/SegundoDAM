class FormularioData {
  late final String nombre;
  late final String telefono;
  late final bool likesFlutter;
  late final double flutterSliderValue;

  FormularioData({
    required this.nombre,
    required this.telefono,
    required this.likesFlutter,
    required this.flutterSliderValue,
  });

  // Constructor para convertir de Map (Base de datos) a Objeto [cite: 5]
  FormularioData.fromMap(Map<String, dynamic> map) {
    this.nombre = map['nombre'];
    this.telefono = map['telefono'];
    this.likesFlutter = map['likesFlutter'] == 1; // 1 es true [cite: 5]
    this.flutterSliderValue = map['flutterSliderValue'];
  }

  // Método para convertir de Objeto a Map (Para insertar en BD) [cite: 6]
  Map<String, dynamic> toMap() {
    return {
      'nombre': nombre,
      'telefono': telefono,
      'likesFlutter': likesFlutter ? 1 : 0, // true es 1 [cite: 6]
      'flutterSliderValue': flutterSliderValue,
    };
  }
}