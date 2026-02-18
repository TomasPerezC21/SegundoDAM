import 'package:flutter/material.dart';

import 'package:flutter/services.dart';



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

                  decoration: const InputDecoration(

                    labelText: 'Nombre (max. 50 caracteres)',



                  ),

                  validator: (value) {

                    if (value == null || value.isEmpty) {

                      return 'Por favor, introduce tu nombre.';

                    }

                    return null;

                  },

                  onSaved: (value) => _nombre = value ?? '',

                ),

                TextFormField(

                  keyboardType: TextInputType.phone,

                  inputFormatters: [FilteringTextInputFormatter.digitsOnly],

                  decoration: const InputDecoration(

                    labelText: 'Número de teléfono móvil',

                  ),

                  validator: (value) {

                    if (value == null || value.isEmpty) {

                      return 'Por favor, introduce tu número de teléfono móvil';

                    }

                    return null;

                  },

                  onSaved: (value) => _telefono = value ?? '',

                ),

                CheckboxListTile(

                  title: const Text('¿Te gustaría trabajar con Flutter?'),

                  value: _quiereTrabajar,

                  onChanged: (bool? value) {

                    setState(() {

                      _quiereTrabajar = value ?? false;

                    });

                  },

                ),

                Column(

                  crossAxisAlignment: CrossAxisAlignment.start,

                  children: [

                    const Text('¿Cuánto te gusta Flutter?'),

                    Slider(

                      value: _gustosidad,

                      min: 0,

                      max: 10,

                      divisions: 10,

                      label: _gustosidad.round().toString(),

                      onChanged: (double value) {

                        setState(() {

                          _gustosidad = value;

                        });

                      },

                    ),

                    FormField<bool>(

                      initialValue: _aceptaCondiciones,

                      validator: (value) {

                        if (value == false) {

                          return 'debes aceptar las condiciones';

                        }

                        return null;

                      },

                      builder: (state) {

                        return Column(

                          crossAxisAlignment: CrossAxisAlignment.start,

                          children: [

                            CheckboxListTile(

                              title: const Text('Aceptar condiciones de servicio'),

                              value: _aceptaCondiciones,

                              onChanged: (bool? value) {

                                setState(() {

                                  _aceptaCondiciones = value ?? false;

                                  state.didChange(value);

                                });

                              },

                            ),

// Si hay error, mostramos el texto en rojo

                            if (state.hasError)

                              Padding(

                                padding: const EdgeInsets.symmetric(horizontal: 16),

                                child: Text(

                                  state.errorText!,

                                  style: const TextStyle(color: Colors.red, fontSize: 12),

                                ),

                              ),

                          ],

                        );

                      },



                    ),

                    ElevatedButton(



                      onPressed: () {

// Validamos todos los campos a la vez

                        if (_formKey.currentState!.validate()) {

                          _formKey.currentState!.save();



                          Map<String, dynamic> formDatos = {

                            'nombre': _nombre,

                            'telefono': _telefono,

                            'trabajar': _quiereTrabajar,

                            'gustosidad': _gustosidad,

                          };



                          Navigator.pushNamed(

                            context,

                            '/enviado',

                            arguments: formDatos,

                          );

                        }

                      },

                      child: const Text('Enviar'),

                    )

                  ],

                ),

              ],

            ),

          ),

        ),

      ),

    );

  }

}