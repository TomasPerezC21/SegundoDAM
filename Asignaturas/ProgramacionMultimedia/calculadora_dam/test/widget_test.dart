// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility in the flutter_test package. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:calculadora_dam/main.dart';
import 'package:calculadora_dam/screens/CalculadoraHomePage.dart';

void main() {
  // Grupo de pruebas para deleteAll
  group('deleteAll method tests', () {
    test('deleteAll should clear the _str list', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = ['1', '*', '3'];
      calculadoraState.deleteAll();
      expect(calculadoraState.str.isEmpty, true,
          reason: "La lista str debería estar vacía");
    });
  });



  // Grupo de pruebas para deleteOne
  group('deleteOne method tests', () {
    test('deleteOne no hace nada cuando la lista esta vacia', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.deleteOne();
      expect(calculadoraState.str.isEmpty, true);
    });

    test(
        'deleteOne elimina el ultimo caracter cuando el ultimo elemento es un digito',
            () {
          final calculadoraState = CalculadoraHomePageState();
          calculadoraState.str = ['123'];
          calculadoraState.deleteOne();
          expect(calculadoraState.str.last, '12');
        });
    test('deleteOne elimina last cuando ya no quedan digitos despues de borrar',
            () {
          final calculadoraState = CalculadoraHomePageState();
          calculadoraState.str = ['1', '+', '2'];
          calculadoraState.deleteOne();
          expect(calculadoraState.str, ['1', '+'], reason: "no elimina nodo");
        });
  });





  // Grupo de pruebas para add
  group('add method tests', () {
    test('add agrega un numero cuando la lista esta vacia y no es un operador',
            () {
          final calculadoraState = CalculadoraHomePageState();
          calculadoraState.str = [];
          calculadoraState.add('1');
          expect(calculadoraState.str, ['1'],
              reason: "La lista debe contener '1' después de agregarlo.");
        });

    test('add no agrega un operador cuando la lista esta vacia', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('+');
      expect(calculadoraState.str.isEmpty, true,
          reason:
          "La lista no debe contener ningún operador cuando está vacía.");
    });

    test('add agrega un numero despues de un operador', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('2');
      calculadoraState.add('+');
      calculadoraState.add('1');
      expect(calculadoraState.str, ['2', '+', '1'],
          reason: "La lista debe contener el operador y el número.");
    });

    test('add no agrega un numero despues de otro numero', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('1');
      calculadoraState.add('2');
      expect(calculadoraState.str, isNot(['1', '2']),
          reason: "La lista debe combinar los números '1' y '2' en uno solo.");
      expect(calculadoraState.str, ['12'],
          reason: "La lista debe combinar los números '1' y '2' en uno solo.");
    });

    test('add agrega un operador despues de un numero', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('1');
      calculadoraState.add('+');
      expect(calculadoraState.str, ['1', '+'],
          reason: "La lista debe contener el número y luego el operador.");
      calculadoraState.add('1');
      calculadoraState.add('+');
      expect(calculadoraState.str, ['1', '+', '1', '+'],
          reason: "La lista debe contener el número y luego el operador.");
    });

    test('add no agrega un operador despues de otro operados', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('1');
      calculadoraState.add('+');
      calculadoraState.add('/');
      expect(calculadoraState.str, ['1', '+'],
          reason: "La lista debe contener el número y luego el operador.");
    });

    test('add no agrega un punto si ya existe uno en el numero actual', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = [];
      calculadoraState.add('1');
      calculadoraState.add('.');
      calculadoraState.add('2');
      calculadoraState.add('.');
      calculadoraState.add('2');
      expect(calculadoraState.str, ['1.22'],
          reason: "La lista debe contener '1.22' sin agregar más de un punto.");
    });

    test('add agrega un punto solo si no existe un punto en el numero actual',
            () {
          final calculadoraState = CalculadoraHomePageState();
          calculadoraState.str = [];
          calculadoraState.add('1');
          calculadoraState.add('.');
          calculadoraState.add('2');
          expect(calculadoraState.str, ['1.2'],
              reason: "La lista debe contener el número '1.2'.");
        });
  });

  group('getResult method tests', () {
    test('getResult no hace nada cuando la operacion es invalida', () {
      final calculadoraState = CalculadoraHomePageState();
      calculadoraState.str = ['1'];
      calculadoraState.add('*');  // Agregamos el operador inválido '*'

      // Aquí puedes simular el proceso de cálculo sin que dependa del Scaffold o SnackBar.
      calculadoraState.getResult();

      // Verificamos que la lista no cambió, ya que la operación es inválida.
      expect(calculadoraState.str, equals(['1', '*']),
          reason: "La operación no debería modificar la lista debido a un error.");
    });


  });
}
