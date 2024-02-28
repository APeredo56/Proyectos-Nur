import 'package:flutter/material.dart';

class ListBuilderPage extends StatelessWidget {
  const ListBuilderPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("List"),
        ),
        body: getListView());
  }

  getListView() {
    var items = List<String>.generate(10000, (i) => "Item $i");
    return ListView.builder(
      itemCount: items.length,
      itemBuilder: (context, index) {
        return ListTile(
          title: Text(items[index]),
        );
      },
    );
  }
}
