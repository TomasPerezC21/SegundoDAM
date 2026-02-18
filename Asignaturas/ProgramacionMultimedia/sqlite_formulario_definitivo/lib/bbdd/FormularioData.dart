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

  // Convertidor de Mapa a Objeto (para leer de la DB)
  FormularioData.fromMap(Map<String, dynamic> map) {
    this.nombre = map['nombre'];
    this.telefono = map['telefono'];
    this.likesFlutter = map['likesFlutter'] == 1; // 1 es true
    this.flutterSliderValue = map['flutterSliderValue'];
  }

  // Convertidor de Objeto a Mapa (para insertar en la DB)
  Map<String, dynamic> toMap() {
    return {
      'nombre': nombre,
      'telefono': telefono,
      'likesFlutter': likesFlutter ? 1 : 0, // true es 1, false es 0
      'flutterSliderValue': flutterSliderValue,
    };
  }
}