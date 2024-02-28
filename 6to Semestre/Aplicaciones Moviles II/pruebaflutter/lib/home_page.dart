import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: getBody(),
    );
  }

  ListView getBody() => ListView(
        children: [
          const Text("Hola mundo"),
          const Icon(Icons.ac_unit),
          Image.network(
              "https://cdn.bolivia.com/gastronomia/2013/07/30/empanadas-de-queso-para-api-3245.jpg"),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/about");
              },
              child: const Text("Ir a about")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/list");
              },
              child: const Text("Ir a la lista")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/list2");
              },
              child: const Text("Ir a la lista 2")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/list3");
              },
              child: const Text("Ir a la lista 3")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/list4");
              },
              child: const Text("Ir a la lista 4")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/card");
              },
              child: const Text("Ir al card")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/form");
              },
              child: const Text("Ir al form")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/formpersona");
              },
              child: const Text("Ir al form persona")),
          // ElevatedButton(
          //     onPressed: () {
          //       Navigator.pushNamed(context, "/postlist");
          //     },
          //     child: const Text("Ir a la lista de posts")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/personalist");
              },
              child: const Text("Ir a la lista de personas")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/formdb");
              },
              child: const Text("Formulario de personas")),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/sockettest");
              },
              child: const Text("Sockets"))
        ],
      );
}
