import 'package:pruebaflutter/models/persona_model.dart';
import 'package:pruebaflutter/providers/database_provider.dart';

class PersonaBLL {
  static Future<int> insertPersona(Persona persona) async {
    final db = await DatabaseProvider.database;
    var res = await db.insert("personas", persona.toJson());
    return res;
  }

  static Future<List<Persona>> selectAll() async {
    final db = await DatabaseProvider.database;
    var res = await db.query("personas");
    List<Persona> list =
        res.isNotEmpty ? res.map((c) => Persona.fromJson(c)).toList() : [];
    return list;
  }
}
