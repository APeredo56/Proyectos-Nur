import 'package:flutter/material.dart';
import 'package:movies/home_page.dart';
import 'package:flutter/gestures.dart';
import 'package:movies/movie_detail_page.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Movies',
      scrollBehavior: CustomScrollBehavior(),
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: "/",
      routes: {
        "/":(context) => const HomePage(),
        "/movie":(context) => const MovieDetailPage(),
      },
    );
  }

  
}

class CustomScrollBehavior extends MaterialScrollBehavior {
  @override
  Set<PointerDeviceKind> get dragDevices => {
        PointerDeviceKind.touch,
        PointerDeviceKind.mouse,
      };
}


