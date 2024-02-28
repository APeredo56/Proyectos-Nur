import 'package:flutter/material.dart';

class ListDividerPage extends StatelessWidget {
  const ListDividerPage({super.key});

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
    return ListView.separated(
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(items[index]),
          );
        },
        separatorBuilder: (context, index) {
          return const Divider();
        },
        itemCount: items.length);
  }
}
