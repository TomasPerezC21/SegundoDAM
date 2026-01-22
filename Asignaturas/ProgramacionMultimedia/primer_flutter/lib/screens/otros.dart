import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TabsWidget extends StatelessWidget {
  TabsWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 3,
        child: Scaffold(
            appBar: AppBar(
              title: Text("El señor de los anillos"),
              bottom: TabBar(
                tabs: <Widget>[
                  Tab(text: 'Parte #1', icon: Icon(Icons.keyboard_arrow_left)),
                  Tab(text: 'Parte #2', icon: Icon(Icons.keyboard_arrow_down)),
                  Tab(text: 'Parte #3', icon: Icon(Icons.keyboard_arrow_right))
                ],
              ),
            ),
            body: TabBarView(
              children: <Widget>[Tab1(), Tab2(), Tab3()],
            ),
            bottomNavigationBar: Container(
                child: TabBar(labelColor: Colors.black, tabs: <Widget>[
                  Tab(text: 'Parte #1', icon: Icon(Icons.keyboard_arrow_left)),
                  Tab(text: 'Parte #2', icon: Icon(Icons.keyboard_arrow_up)),
                  Tab(text: 'Parte #3', icon: Icon(Icons.keyboard_arrow_right))
                ]))));
  }
}

class Tab1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Image.network(
        "https://covers.openlibrary.org/b/isbn/9786070712722-L.jpg");
  }
}
class Tab2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Image.network(
        "https://covers.openlibrary.org/b/isbn/9789706906526-L.jpg");
  }
}
class Tab3 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Image.network(
        "https://covers.openlibrary.org/b/isbn/9505470665-L.jpg");
  }
}


