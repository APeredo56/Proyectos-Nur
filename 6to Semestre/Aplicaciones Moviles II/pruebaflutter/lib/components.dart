import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

Future<void> _selectDate(BuildContext context, TextEditingController controller,
    Function(String)? onChanged) async {
  final date = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2000),
      lastDate: DateTime(2025));
  if (date != null) {
    controller.text = DateFormat('dd/MM/yyyy').format(date);
    if (onChanged != null) {
      onChanged(DateFormat('yyyy-MM-dd').format(date));
    }
  }
}

Widget getDatePicker(BuildContext context, TextEditingController dateController,
    String label, Function(String)? onChanged) {
  return ListTile(
    contentPadding: EdgeInsets.zero,
    title: TextFormField(
      controller: dateController,
      readOnly: true,
      onTap: () {
        _selectDate(context, dateController, onChanged);
      },
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Por favor ingrese una $label';
        }
        String pattern = r'^\d{2}\/\d{2}\/\d{4}$';
        RegExp regExp = RegExp(pattern);
        if (!regExp.hasMatch(value)) {
          return 'Por favor ingrese una $label válida';
        }
        return null;
      },
      decoration: InputDecoration(
        border: const OutlineInputBorder(),
        labelText: label,
      ),
    ),
    trailing: IconButton(
        icon: const Icon(Icons.calendar_today),
        onPressed: () {
          _selectDate(context, dateController, onChanged);
        }),
  );
}

Widget getTextField(TextEditingController controller, String label) {
  return TextFormField(
    controller: controller,
    decoration: InputDecoration(
      border: const OutlineInputBorder(),
      labelText: label,
    ),
    validator: (value) {
      if (value == null || value.isEmpty) {
        return 'Por favor ingrese un $label';
      }
      return null;
    },
  );
}

Widget getNumberField(TextEditingController controller, String label) {
  return TextFormField(
    keyboardType: TextInputType.number,
    controller: controller,
    decoration: InputDecoration(
      border: const OutlineInputBorder(),
      labelText: label,
    ),
    validator: (value) {
      if (value == null || value.isEmpty) {
        return 'Por favor ingrese un $label';
      }
      if (int.tryParse(value) == null) {
        return 'Por favor ingrese un $label válido';
      }
      return null;
    },
  );
}
