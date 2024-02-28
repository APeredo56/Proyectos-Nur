import 'dart:convert';

Persona personaFromJson(String str) => Persona.fromJson(json.decode(str));

String personaToJson(Persona data) => json.encode(data.toJson());

class Persona {
  int? id;
  String nombres;
  String apellidos;
  int edad;
  String ciudad;
  DateTime fechaNacimiento;

  Persona({
    this.id,
    required this.nombres,
    required this.apellidos,
    required this.edad,
    required this.ciudad,
    required this.fechaNacimiento,
  });

  factory Persona.fromJson(Map<String, dynamic> json) => Persona(
        id: json["id"],
        nombres: json["nombres"],
        apellidos: json["apellidos"],
        edad: json["edad"],
        ciudad: json["ciudad"],
        fechaNacimiento: DateTime.parse(json["fechaNacimiento"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "nombres": nombres,
        "apellidos": apellidos,
        "edad": edad,
        "ciudad": ciudad,
        "fechaNacimiento":
            "${fechaNacimiento.year.toString().padLeft(4, '0')}-${fechaNacimiento.month.toString().padLeft(2, '0')}-${fechaNacimiento.day.toString().padLeft(2, '0')}",
      };
}
