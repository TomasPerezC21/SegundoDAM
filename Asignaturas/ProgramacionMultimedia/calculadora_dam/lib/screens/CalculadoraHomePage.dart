import 'package:flutter/material.dart';
class CalculadoraHomePage extends StatefulWidget {
  const CalculadoraHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  CalculadoraHomePageState createState() => CalculadoraHomePageState();
}


class CalculadoraHomePageState extends State<CalculadoraHomePage> {
  List<String> str=[];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title),),
      body: Column(
          children: [
        Expanded(
          child: Card(
            child: Center(child: Center(child: Text(str.toString()))),
          ),
        ),
        Row(
          children: [
            buildButton('C'),
            buildButton('CE')]
        ),
        Row(
          children: [
            buildButton('7'),
            buildButton('8'),
            buildButton('9'),
            buildButton('/'),
          ],
        ),
        Row(
          children: [
            buildButton('4'),
            buildButton('5'),
            buildButton('6'),
            buildButton('*'),
          ],
        ),
        Row(
          children: [
            buildButton('1'),
            buildButton('2'),
            buildButton('3'),
            buildButton('-'),
          ],
        ),
        Row(
          children: [
            buildButton('0'),
            buildButton('.'),
            buildButton('='),
            buildButton('+'),
          ],
        )
      ]),

    );
  }

  Widget buildButton(String text) {
    return Expanded(child: TextButton(
      onPressed: () {
        accionBoton(text);
      },
      style: TextButton.styleFrom(
        backgroundColor: text=='C' || text=='CE'? Colors.grey:int.tryParse(text) != null || text == '.'? Colors.blue: Colors.blueGrey,
      ),
      child: Text(text,
      style: TextStyle(color: Colors.black)
      ),
    ),
    );
  }

  void accionBoton(String buttonText) {
    setState(() {
      if (buttonText == 'C') {
        deleteAll();
      } else if (buttonText == 'CE') {
        deleteOne();
      }else if (buttonText == '=') {
        String resultado = getResult();
        str.clear();
        str.add(resultado);
      } else {
        add(buttonText);
      }
    });
  }

  void deleteAll() {
    str.clear();
  }

  void deleteOne() {

  }

  String getResult() {
    String result = "";

    //multiplicacion division
    for (int i = 0; i<str.length; i++){
      if (str[i] == "*") {
        double anterior = double.parse(str[i - 1]);
        double posterior = double.parse(str[i + 1]);
        double res = anterior * posterior;

        str[i - 1] = res.toString();
        str.removeAt(i);
        str.removeAt(i);
        i--;
      }else if (str[i] == "/") {
        double anterior = double.parse(str[i - 1]);
        double posterior = double.parse(str[i + 1]);
        double res = anterior / posterior;

        str[i - 1] = res.toString();
        str.removeAt(i);
        str.removeAt(i);
        i--;
      }
    }

    //suma y resta
    for (int i = 0; i<str.length; i++){
      if (str[i] == "+") {
        double anterior = double.parse(str[i - 1]);
        double posterior = double.parse(str[i + 1]);
        double res = anterior + posterior;

        str[i - 1] = res.toString();
        str.removeAt(i);
        str.removeAt(i);
        i--;
      }else if (str[i] == "-") {
        double anterior = double.parse(str[i - 1]);
        double posterior = double.parse(str[i + 1]);
        double res = anterior - posterior;

        str[i - 1] = res.toString();
        str.removeAt(i);
        str.removeAt(i);
        i--;
      }
    }

    return result;
  }

  void add(String buttonText) {

    if(str.isEmpty){
      if(!"+-*/".contains(buttonText)){
        str.add(buttonText);
      }
    }else if("+-*/".contains(buttonText) && !'+-*/'.contains(str.last)){
      str.add(buttonText);
    }else if(!'+-*/'.contains(buttonText) && !'+-*/'.contains(str.last)){
      str.last+=buttonText;
    }else if('+-*/'.contains(str.last) && !'+-*/'.contains(buttonText)){
      str.add(buttonText);
    }

    }

  }


