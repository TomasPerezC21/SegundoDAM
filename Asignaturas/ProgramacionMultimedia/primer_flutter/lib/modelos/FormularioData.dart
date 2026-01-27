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

  FormularioData.fromMap(Map<String, dynamic> map) {
    this.nombre = map['nombre'];
    this.telefono = map['telefono'];
    this.likesFlutter = map['likesFlutter'] == 1;
    this.flutterSliderValue = map['flutterSliderValue'];
  }


  Map<String, dynamic> toMap() {
    return {
      'nombre': nombre,
      'telefono': telefono,
      'likesFlutter': likesFlutter ? 1 : 0,
      'flutterSliderValue': flutterSliderValue,
    };
  }
}