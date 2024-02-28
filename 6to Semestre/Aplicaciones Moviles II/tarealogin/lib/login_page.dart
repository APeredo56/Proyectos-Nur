import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _emailController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("Iniciar Sesión"),
        ),
      body: Container(
        padding: const EdgeInsets.all(10),
        child:  getForm()
      )
    );
  }

  getForm(){
    return ListView(
      children: <Widget>[
        const SizedBox(height: 20),
        const Text("Correo electrónico"),
        const SizedBox(height: 10),
        TextField(
          controller: _emailController,
          decoration: const InputDecoration(
            border: OutlineInputBorder(),
            hintText: 'Correo electrónico',
          ),
        ),
        const SizedBox(height: 20),
        const Text("Contraseña"),
        const SizedBox(height: 10),
        TextField(
          controller: _passwordController,
          obscureText: true,
          decoration: const InputDecoration(
            border: OutlineInputBorder(),
            hintText: 'Contraseña',
          ),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () {
            if (_emailController.text == "admin" && _passwordController.text == "admin") {
              showToast("Bienvenido admin");
            } else{
              showToast("Usuario o contraseña incorrectos");
            }
          },
          child: const Text('Iniciar Sesión'),
        ),
        const SizedBox(height: 20),
      ]
    );
  }

  void showToast(String message) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(message),
        duration: const Duration(seconds: 2),
      ),
    );
  }
}