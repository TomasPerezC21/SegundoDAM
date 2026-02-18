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

  // Constructor para crear el objeto desde un mapa (Base de Datos)
  FormularioData.fromMap(Map<String, dynamic> map) {
    this.nombre = map['nombre'];
    this.telefono = map['telefono'];
    this.likesFlutter = map['likesFlutter'] == 1; // Convierte entero a bool [cite: 5]
    this.flutterSliderValue = map['flutterSliderValue'];
  }

  // Método para convertir el objeto a un mapa (Para insertar en BD)
  Map<String, dynamic> toMap() {
    return {
      'nombre': nombre,
      'telefono': telefono,
      'likesFlutter': likesFlutter ? 1 : 0, // Convierte bool a entero
      'flutterSliderValue': flutterSliderValue,
    };
  }
}