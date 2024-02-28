import 'package:flutter/material.dart';
import 'package:pruebaflutter/bll/persona_bll.dart';
import 'package:pruebaflutter/models/persona_model.dart';

class PersonaListPage extends StatelessWidget {
  const PersonaListPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Personas'),
        ),
        body: FutureBuilder(
          future: PersonaBLL.selectAll(),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return getListView(snapshot.data);
            } else if (snapshot.hasError) {
              print(snapshot.error);
              return const Text("Error");
            }

            return const Center(child: CircularProgressIndicator());
          },
        ));
  }

  Widget getListView(List<Persona>? data) {
    if (data == null) {
      return const Text("Error");
    }
    return ListView.builder(
      itemCount: data.length,
      itemBuilder: (context, index) {
        return ListTile(
          leading: const Icon(Icons.person),
          title: Text(data[index].nombres),
          onTap: () {
            // Navigator.pushNamed(context, '/personadetail',
            //     arguments: {'id': data[index].id});
          },
        );
      },
    );
  }
}
