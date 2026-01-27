import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class FormularioPage extends StatefulWidget {
  const FormularioPage({Key? key}) : super(key: key);

  @override
  _FormularioPageState createState() => _FormularioPageState();
}

class _FormularioPageState extends State<FormularioPage> {

  String _nombre = '';
  String _email = '';
  String _password = '';
  final TextEditingController controllerInputNombre = TextEditingController();
  int _valorSeleccionadoDropDown = 1;
  List<String> _optMaterias = ['PMDM', 'SGE', 'LM', 'AADD'];
  String _valormateriaDropDown = 'PMDM';
  bool _checkCurso = false;
  String _radioInicial='Extraordinaria';
  Set<String> _valoresRadio = {'Ordinaria', 'Extraordinaria', 'Excepcional de gracia'};
  double _sliderValor=5.0;
  var _formKey = GlobalKey<FormState>();
  Text _errorCheckbox=Text('',
    style: TextStyle(color: Colors.red),
  );


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Formulario'),
        ),
        body: Form(
          key: _formKey,
          child: ListView(
            padding: EdgeInsets.all(20),
            children: [
              _crearInput(),
              Divider(),
              _crearEmail(),
              Divider(),
              _crearPassword(),
              Divider(),
              _visualizaDatos(),
              Divider(),
              _crearDropDown(),
              Divider(),
              _crearCheckBox(),
              _errorCheckbox,
              Divider(),
              _crearSwitch(),
              Divider(),
              _crearRadio(),
              Divider(),
              _crearSlider(),
              Divider(),
              _crearBoton()

            ],
          ),
        ));
  }

  Widget _crearSlider() {
    return Column(
      children: [
        Text(
          'Valor seleccionado: ${_sliderValor.toStringAsFixed(1)}',
          style: TextStyle(fontSize: 18.0),
        ),
        Slider(
          value: _sliderValor,
          onChanged: (nuevoValor) {
            setState(() {
              _sliderValor = nuevoValor.toDouble();
            });
          },
          label: _sliderValor.toStringAsFixed(1),
          divisions: 100,
          min: 0,
          max: 100,
        ),
      ],
    );
  }

  Widget _crearRadio() {
    return RadioGroup<String>(
      groupValue: _radioInicial,
      onChanged: (valor) {
        setState(() {
          _radioInicial = valor!;
          print(valor);
        });
      },
      child: Column(
        children: _elementosRadio(),
      ),
    );
  }

  List<Widget> _elementosRadio() {
    return _valoresRadio.map((element) {
      return RadioListTile<String>(
        title: Text(element),
        value: element,
      );
    }).toList();
  }

  Widget _crearSwitch(){
    return SwitchListTile(
        title: Text('Aprobado'),
        subtitle: Text('Indica si has aprobado'),
        secondary: Icon(Icons.check_circle),
        value: _checkCurso,
        onChanged: (valor) { setState(() {
          _checkCurso = valor;
        }); });
  }

  Widget _crearCheckBox(){
    return CheckboxListTile(
        title: Text('Cursando'),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0),side: BorderSide(color: Colors.grey)),
        value: _checkCurso,
        onChanged: (valor) {
          setState(() { _checkCurso = valor! ;});
        }
    );
  }

  Widget _crearDropDown() {
    return InputDecorator(
      decoration: InputDecoration(
        labelText: 'Selecciona una materia',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(15.0),
        ),
      ),
      child: DropdownButton(
        value: _valormateriaDropDown,
        onChanged: (String? valor) {
          setState(() {
            _valormateriaDropDown =
                valor ?? 'PMDM'; // valor por defecto si no seleccionas ninguno
          });
        },
        items: _crearMateriasDropDown(),
      ),
    );
  }

  List<DropdownMenuItem<String>> _crearMateriasDropDown(){

    List<DropdownMenuItem<String>> lista = [];
    _optMaterias.forEach((element) {
      lista.add(DropdownMenuItem(child: Text('$element'),value: element,));
    });

    return lista;
  }

  Widget _crearBoton() {

    final estiloBoton = ElevatedButton.styleFrom(
        backgroundColor: Colors.black);
    final estiloTexto = TextStyle(color: Colors.red, fontSize: 20.0);
    return ElevatedButton(
        style: estiloBoton,
        onPressed: () {
          setState(() {
            _enviarFormulario();
          });
        },
        child: Text('ENVIAR', style: estiloTexto));
  }

  Widget _visualizaDatos(){
    return Column(
      children: [
        Text('Nombre del alumno: $_nombre'),
        Text('Email del alumno: $_email'),
        Text('Password: $_password')
      ],
    );
  }

  Widget _crearEmail(){

    return TextField(
      keyboardType: TextInputType.emailAddress,
      decoration: InputDecoration(
          hintText: 'Email del alumno',
          labelText: 'Email',
          suffixIcon: Icon(Icons.email),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          )
      ),
      onChanged: (valor) => setState(() {
        _email = valor;
      }),
    );
  }


  Widget _crearPassword(){

    return TextField(
      obscureText: true,
      obscuringCharacter: '*',
      maxLength: 20,
      decoration: InputDecoration(
          hintText: 'Introduce Password max 20 carácteres',
          labelText: 'Password',
          suffixIcon: Icon(Icons.password),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          )
      ),
      onChanged: (valor) => setState(() {
        _password = valor;
      }),
    );
  }

  Widget _crearInput() {

    return TextFormField(
      validator: (value){
        if(value==null || value.isEmpty){
          return 'por favor, ingresa un valor';
        }
        return null;
      },
      onSaved: (value){
        _nombre=value!;
      },
      textCapitalization: TextCapitalization.words,
      maxLength: 10,
      controller: controllerInputNombre,
      keyboardType: TextInputType.name,
      decoration: InputDecoration(
        border: OutlineInputBorder(     // Establece un borde cicular/otro  alrededor de la caja de texto
          borderRadius: BorderRadius.circular(15.0),
        ),
        //counterText: '',   // Texto que aparece en la parte inferior de la caja indicando el num de caracteres
        //hintText: 'Nombre del alumno',          // Texto que aparece dentro de la caja y desaparece al escribir
        labelText: 'Nombre',                      // Texto qye aparece encima de la caja y desaparece cuando tiene el foco
        helperText: 'Introduce el nombre del alumno',  // Texto que aparece en la parte inferior de la caja
        suffixIcon: Icon(Icons.account_circle),     // Widget que aparece a la derecha dentro de la caja
        icon: Icon(Icons.abc_sharp),          // Widget que aparece a la izquierda fuera de la caja

      ),

    );

  }

  Future<void> _enviarFormulario() async{
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('nombre', 'Tomás');

    final isValid = _formKey.currentState?.validate();
    if (!_checkCurso) {
      print('validando  checkbox');
      setState(() {
        _errorCheckbox=Text('debes de marcar cursando',
          style: TextStyle(color: Colors.red),
        );
      });

      return;
    }

    // Si el formulario es válido, continúa
    _formKey.currentState?.save();

    // resto de acciones
  }

}