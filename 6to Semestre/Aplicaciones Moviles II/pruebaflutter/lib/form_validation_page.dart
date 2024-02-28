import 'package:flutter/material.dart';
import 'components.dart';
import 'package:http/http.dart' as http;

class FormValidationPage extends StatefulWidget {
  const FormValidationPage({super.key});

  @override
  State<FormValidationPage> createState() => _FormValidationPageState();
}

class _FormValidationPageState extends State<FormValidationPage> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController nombreController = TextEditingController();
  TextEditingController apellidoController = TextEditingController();
  TextEditingController dateController = TextEditingController();
  TextEditingController ciudadController = TextEditingController();
  TextEditingController edadController = TextEditingController();
  String selectedDate = "";

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
    );
  }

  getForm() {
    return Form(
      key: _formKey,
      child: Column(
        children: [
          getTextField(nombreController, "Nombre"),
          const SizedBox(
            height: 10,
          ),
          getTextField(apellidoController, "Apellido"),
          const SizedBox(
            height: 10,
          ),
          getTextField(ciudadController, "Ciudad"),
          const SizedBox(
            height: 10,
          ),
          getNumberField(edadController, "Edad"),
          const SizedBox(
            height: 10,
          ),
          DropdownButtonFormField(
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                labelText: 'Genero',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Por favor seleccione un genero';
                }
                return null;
              },
              items: const [
                DropdownMenuItem(
                  value: "M",
                  child: Text("Masculino"),
                ),
                DropdownMenuItem(
                  value: "F",
                  child: Text("Femenino"),
                ),
              ],
              onChanged: (value) {
                print(value);
              }),
          getDatePicker(context, dateController, "Fecha de nacimiento", (date) {
            setState(() {
              selectedDate = date;
            });
          }),
          ElevatedButton(
              onPressed: () {
                if (_formKey.currentState!.validate()) {
                  ScaffoldMessenger.of(context).showSnackBar(
                      const SnackBar(content: Text('Procesando datos')));
                  print('Nombre: ${nombreController.text}');
                  print('Apellido: ${apellidoController.text}');
                  postFormToApi();
                }
              },
              child: const Text("Guardar datos"))
        ],
      ),
    );
  }

  void postFormToApi() {
    var map = new Map<String, dynamic>();
    map['nombres'] = nombreController.text;
    map['apellidos'] = apellidoController.text;
    map['ciudad'] = ciudadController.text;
    map['edad'] = edadController.text;
    map['fechaNacimiento'] = selectedDate;

    http
        .post(Uri.parse('http://personas.jmacboy.com/personas'), body: map)
        .then((value) {
      print(value.body);
    });
  }
}
