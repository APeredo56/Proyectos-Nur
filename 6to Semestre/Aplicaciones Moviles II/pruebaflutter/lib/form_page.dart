import 'package:flutter/material.dart';
import 'package:pruebaflutter/components.dart';

class FormPage extends StatefulWidget {
  const FormPage({super.key});

  @override
  State<FormPage> createState() => _FormPageState();
}

class _FormPageState extends State<FormPage> {
  TextEditingController nombreController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController dateController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("Form"),
        ),
        body: Container(
          child: getForm(),
          padding: const EdgeInsets.all(10),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            showDialog(
                context: context,
                builder: (context) {
                  return AlertDialog(
                    title: const Text("Datos"),
                    content: Text(
                        "Nombre: ${nombreController.text}\nEmail: ${emailController.text}"),
                    actions: [
                      TextButton(
                          onPressed: () {
                            Navigator.pop(context);
                          },
                          child: const Text("Cerrar"))
                    ],
                  );
                });
          },
          child: const Icon(Icons.save),
        ));
  }

  getForm() {
    return ListView(
      children: [
        getTextField(nombreController, "Nombre"),
        const SizedBox(
          height: 10,
        ),
        TextField(
          keyboardType: TextInputType.emailAddress,
          controller: emailController,
          decoration: const InputDecoration(
            border: OutlineInputBorder(),
            labelText: 'Email',
          ),
        ),
        const SizedBox(
          height: 10,
        ),
        getDatePicker(context, dateController, "Fecha de nacimiento", null),
      ],
    );
  }

  Future<void> _selectDate(BuildContext context) async {
    final date = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2000),
        lastDate: DateTime(2025));
    if (date != null) {
      dateController.text = date.toString();
    }
  }
}
