import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:formulario_flutter/bbdd/db_helper.dart';

class Formulario extends StatefulWidget {
  const Formulario({super.key});

  @override
  State<Formulario> createState() => _FormularioState();
}

class _FormularioState extends State<Formulario> {
  final _formKey = GlobalKey<FormState>();
  String _nombre = '';
  String _telefono = '';
  bool _quiereTrabajar = false;
  double _gustosidad = 0.0;
  bool _aceptaCondiciones = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Flutter Formulario Ejercicio')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              children: [
                TextFormField(
                  maxLength: 50,
                  decoration: const InputDecoration(labelText: 'Nombre (max. 50 caracteres)'),
                  validator: (value) => (value == null || value.isEmpty) ? 'Introduce tu nombre' : null,
                  onSaved: (value) => _nombre = value ?? '',
                ),
                TextFormField(
                  keyboardType: TextInputType.phone,
                  inputFormatters: [FilteringTextInputFormatter.digitsOnly],
                  decoration: const InputDecoration(labelText: 'Número de teléfono móvil'),
                  validator: (value) => (value == null || value.isEmpty) ? 'Introduce tu teléfono' : null,
                  onSaved: (value) => _telefono = value ?? '',
                ),
                CheckboxListTile(
                  title: const Text('¿Te gustaría trabajar con Flutter?'),
                  value: _quiereTrabajar,
                  onChanged: (val) => setState(() => _quiereTrabajar = val ?? false),
                ),
                const Text('¿Cuánto te gusta Flutter?'),
                Slider(
                  value: _gustosidad,
                  min: 0, max: 10, divisions: 10,
                  label: _gustosidad.round().toString(),
                  onChanged: (val) => setState(() => _gustosidad = val),
                ),
                FormField<bool>(
                  initialValue: _aceptaCondiciones,
                  validator: (val) => (val == false) ? 'Debes aceptar las condiciones' : null,
                  builder: (state) => Column(
                    children: [
                      CheckboxListTile(
                        title: const Text('Aceptar condiciones de servicio'),
                        value: _aceptaCondiciones,
                        onChanged: (val) {
                          setState(() => _aceptaCondiciones = val ?? false);
                          state.didChange(val);
                        },
                      ),
                      if (state.hasError) Text(state.errorText!, style: const TextStyle(color: Colors.red, fontSize: 12)),
                    ],
                  ),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      _formKey.currentState!.save();

                      // GUARDAR EN SQLITE
                      await DatabaseHelper().insertar({
                        'nombre': _nombre,
                        'telefono': _telefono,
                        'trabajar': _quiereTrabajar ? 1 : 0,
                        'gustosidad': _gustosidad,
                      });

                      if (!mounted) return;
                      Navigator.pushNamed(context, '/enviado');
                    }
                  },
                  child: const Text('Enviar y Guardar'),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}