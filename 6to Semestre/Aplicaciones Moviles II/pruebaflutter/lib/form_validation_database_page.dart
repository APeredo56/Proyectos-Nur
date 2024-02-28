import 'package:flutter/material.dart';
import 'package:pruebaflutter/bll/persona_bll.dart';
import 'package:pruebaflutter/models/persona_model.dart';
import 'components.dart';

class FormValidationDatabasePage extends StatefulWidget {
  const FormValidationDatabasePage({super.key});

  @override
  State<FormValidationDatabasePage> createState() =>
      _FormValidationDatabasePageState();
}

class _FormValidationDatabasePageState
    extends State<FormValidationDatabasePage> {
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
                  sendToDb();
                }
              },
              child: const Text("Guardar datos"))
        ],
      ),
    );
  }

  void sendToDb() {
    var persona = Persona(
        nombres: nombreController.text,
        apellidos: apellidoController.text,
        ciudad: ciudadController.text,
        edad: int.parse(edadController.text),
        fechaNacimiento: DateTime.parse(selectedDate));
    PersonaBLL.insertPersona(persona).then((value) => {
          ScaffoldMessenger.of(context)
              .showSnackBar(const SnackBar(content: Text('Datos guardados')))
        });
  }
}
