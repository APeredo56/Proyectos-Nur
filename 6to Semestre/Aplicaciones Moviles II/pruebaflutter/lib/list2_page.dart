import 'package:flutter/material.dart';

class Lista2Page extends StatelessWidget {
  const Lista2Page({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("List"),
        ),
        body: ListView(
          children: [
            const Text("Item 1"),
            Image.network(
                "https://cdn.bolivia.com/gastronomia/2013/07/30/empanadas-de-queso-para-api-3245.jpg"),
            const Text("Item 2"),
            const ElevatedButton(onPressed: null, child: const Text("test"))
          ],
        ));
  }
}
