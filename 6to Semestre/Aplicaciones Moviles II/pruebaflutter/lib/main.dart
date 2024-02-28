import 'package:flutter/material.dart';
import 'package:pruebaflutter/about_page.dart';
import 'package:pruebaflutter/card_page.dart';
import 'package:pruebaflutter/form_page.dart';
import 'package:pruebaflutter/form_validation_database_page.dart';
import 'package:pruebaflutter/form_validation_page.dart';
import 'package:pruebaflutter/home_page.dart';
import 'package:pruebaflutter/list2_page.dart';
import 'package:pruebaflutter/list_page.dart';
import 'package:pruebaflutter/listbuilder_page.dart';
import 'package:pruebaflutter/listdivider_page.dart';
import 'package:pruebaflutter/persona_list_page.dart';
import 'package:pruebaflutter/post_detail_page.dart';
import 'package:pruebaflutter/posts_page.dart';
import 'package:pruebaflutter/socket_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: "/",
      routes: {
        "/": (context) => const MyHomePage(title: "Home"),
        "/about": (context) => const AboutPage(),
        "/list": (context) => const ListPage(),
        "/list2": (context) => const Lista2Page(),
        "/list3": (context) => const ListBuilderPage(),
        "/list4": (context) => const ListDividerPage(),
        "/card": (context) => const CardPage(),
        "/form": (context) => const FormPage(),
        "/formpersona": (context) => const FormValidationPage(),
        "/postlist": (context) => const PostsPage(),
        '/postdetail': (context) => const PostDetailPage(),
        '/personalist': (context) => const PersonaListPage(),
        '/formdb': (context) => const FormValidationDatabasePage(),
        '/sockettest': (context) => const SocketPage(),
      },
    );
  }
}
