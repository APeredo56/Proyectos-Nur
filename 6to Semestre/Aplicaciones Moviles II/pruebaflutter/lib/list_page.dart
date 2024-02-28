import 'package:flutter/material.dart';

class ListPage extends StatelessWidget {
  const ListPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("List"),
        ),
        body: getBody());
  }

  getBody() {
    return getList();
  }

  getList() {
    var items = List<String>.generate(10000, (i) => "Item $i");
    var widgets = <Widget>[];
    for (var item in items) {
      widgets.add(
        ListTile(
          title: Text(item),
        ),
      );
    }
    return ListView(
      children: widgets,
    );
  }
}
