import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:pruebaflutter/models/post_model.dart';

class PostsPage extends StatelessWidget {
  const PostsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Posts'),
        ),
        body: FutureBuilder(
          future: getPostList(),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return getListView(snapshot.data);
            } else if (snapshot.hasError) {
              return const Text("Error");
            }

            return const Center(child: CircularProgressIndicator());
          },
        ));
  }

  Future<http.Response> getPostList() {
    return http.get(Uri.parse('https://jsonplaceholder.typicode.com/posts'));
  }

  Widget getListView(http.Response? data) {
    if (data == null) {
      return const Text("Error");
    }
    List<Post> postList = postListFromJson(data.body);
    return ListView.builder(
      itemCount: postList.length,
      itemBuilder: (context, index) {
        return ListTile(
          leading: const Icon(Icons.insert_drive_file_outlined),
          title: Text(postList[index].title),
          onTap: () {
            Navigator.pushNamed(context, '/postdetail',
                arguments: {'id': postList[index].id});
          },
        );
      },
    );
  }
}
