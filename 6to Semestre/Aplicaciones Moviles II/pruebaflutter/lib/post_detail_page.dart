import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:pruebaflutter/models/post_model.dart';

class PostDetailPage extends StatelessWidget {
  const PostDetailPage({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = (ModalRoute.of(context)?.settings.arguments ??
        <String, dynamic>{}) as Map;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Post Detail'),
      ),
      body: FutureBuilder(
        future: getPostDetail(arguments['id']),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return getItem(snapshot.data);
          } else if (snapshot.hasError) {
            return const Text("Error");
          }

          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }

  Future<http.Response> getPostDetail(int id) {
    return http
        .get(Uri.parse('https://jsonplaceholder.typicode.com/posts/$id'));
  }

  Widget getItem(http.Response? data) {
    if (data == null) {
      return const Text("Error");
    }
    Post objPost = postFromJson(data.body);
    return Container(
      margin: const EdgeInsets.all(10),
      child: Card(
        child: Container(
          // wrap container to content
          padding: const EdgeInsets.all(10),
          child: Wrap(children: [
            Text(objPost.title, style: const TextStyle(fontSize: 20)),
            Text(objPost.body),
          ]),
        ),
      ),
    );
  }
}
