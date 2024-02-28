import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'package:movies/models/movie_credits_model.dart';
import 'package:movies/models/movie_detail_model.dart';

class MovieDetailPage extends StatelessWidget {
  const MovieDetailPage({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = (ModalRoute.of(context)?.settings.arguments??<String, dynamic>{}) as Map;
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color(int.parse('0xFF0C0A33')),
        iconTheme: const IconThemeData(color: Colors.white),
        title: const Text("Detalles", style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold))
      ),
      body: FutureBuilder(
        future: getMovieDetail(arguments["id"]),
        builder: (context, snapshot) {
            if(snapshot.hasData){
            return getMovieItem(snapshot.data);
          } else if (snapshot.hasError){
            return const Text("Error al cargar los datos");
          }
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }

  Future<http.Response> getMovieDetail(int id){
    Uri uri = Uri.parse("https://api.themoviedb.org/3/movie/$id?language=es");
    String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YWQxOTJlYWNiOWU2ZGMxOTZjYjdmM2E1NzAwZDk0NCIsInN1YiI6IjY1MjM0MzBmYjNmNmY1MDBjNWZmMzM4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q7p9fFs7UCnpTikch6Pc8kfwUz1x3gwqUXLnKdlYEio";
    return http.get(uri, headers: {"Accept": "application/json", "Authorization": apiKey});  
  }

  Future<http.Response> getMovieCredits(int id){
    Uri uri = Uri.parse("https://api.themoviedb.org/3/movie/$id/credits?language=es");
    String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YWQxOTJlYWNiOWU2ZGMxOTZjYjdmM2E1NzAwZDk0NCIsInN1YiI6IjY1MjM0MzBmYjNmNmY1MDBjNWZmMzM4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q7p9fFs7UCnpTikch6Pc8kfwUz1x3gwqUXLnKdlYEio";
    return http.get(uri, headers: {"Accept": "application/json", "Authorization": apiKey});
  }


  Widget getMovieItem(http.Response? data){
    MovieDetail movie = movieDetailFromJson(data?.body??"");
    return Container(
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
            color: Colors.black,
            image: DecorationImage(
              fit: BoxFit.cover,
              colorFilter: ColorFilter.mode(Colors.black.withOpacity(0.3), BlendMode.dstATop),
              image: getMovieImage(movie.posterPath)
            ),
          ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(movie.title??"", style: getStyle(30, FontWeight.bold, Colors.white)),
          RichText(
            text: TextSpan(
              style: getStyle(20, FontWeight.normal, Colors.white),
              children: [
                TextSpan(text: movie.genres?.map((e) => e.name).join(", ")),
                const TextSpan(
                  text: ' \u2022 ', 
                  style: TextStyle(fontWeight: FontWeight.bold, textBaseline: TextBaseline.ideographic)
                ),
                TextSpan(
                  text: movie.releaseDate!=null? DateFormat("dd/MM/yyyy").format(movie.releaseDate!):''
                ),
                const TextSpan(
                  text: ' \u2022 ', 
                  style: TextStyle(fontWeight: FontWeight.bold, textBaseline: TextBaseline.ideographic)
                ),
                TextSpan(text: "${durationToString(movie.runtime??0)}m"),
              ]
            )
          ),
          const SizedBox(height: 10),
          Text(movie.overview??"", style: getStyle(12, FontWeight.normal, Colors.white)),
          const SizedBox(height: 10),
          Text("Reparto", style: getStyle(20, FontWeight.normal, Colors.white)),
          const SizedBox(height: 10),
          Expanded(child: 
            FutureBuilder(
              future: getMovieCredits(movie.id), 
              builder: (context, snapshot) {
                if(snapshot.hasData){
                  return getCreditsItem(snapshot.data);
                } else if (snapshot.hasError){
                  return const Text("Error al cargar los datos");
                }
                return const Center(child: CircularProgressIndicator());
              }, 
            ),
          ),
        ],
      ),
    );
  }

  Widget getCreditsItem(http.Response? data){
    MovieCredits credits = movieCreditsFromJson(data?.body??"");
    List<CreditItemModel> mergedList = [];
    for (var element in credits.crew) {
      if (element.job == "Director"){
        mergedList.insert(0, CreditItemModel(name: element.name, role: element.job??""));
      }
    }
    for (var element in credits.cast) {
      mergedList.add(CreditItemModel(name: element.name, role: element.character??""));
    }
    return GridView.builder(
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 4,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
      ),
      itemCount: mergedList.length,
      itemBuilder: (BuildContext context, int index) {
        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              mergedList[index].name, style: getStyle(12, FontWeight.bold, Colors.white),
              overflow: TextOverflow.ellipsis,
              maxLines: 2,
            ),
            Text(
              mergedList[index].role, style: getStyle(10, FontWeight.normal, Colors.white), 
              overflow: TextOverflow.ellipsis,
              maxLines: 2,
            ),
          ],
        );
      }
    );
  } 

  String durationToString(int minutes) {
    var d = Duration(minutes:minutes);
    List<String> parts = d.toString().split(':');
    return '${parts[0].padLeft(2, '0')}:${parts[1].padLeft(2, '0')}';
}

  TextStyle getStyle(double fontSize, FontWeight fontWeight, Color color){
    return TextStyle(fontSize: fontSize, fontWeight: fontWeight, color: color);
  }

  getMovieImage(String? path){
    if (path == null || path == ""){
      return const AssetImage("images/no_img_icon.png");
    }
    return NetworkImage("https://image.tmdb.org//t/p/w600_and_h900_bestv2$path");
  }
}

class CreditItemModel{
  String name;
  String role;

  CreditItemModel({required this.name, required this.role});
}