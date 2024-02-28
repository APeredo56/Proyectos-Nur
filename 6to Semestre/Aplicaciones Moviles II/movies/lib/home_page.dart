import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'package:movies/models/search_model.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  TextEditingController _nameController = TextEditingController();
  List<Result> results = [];
  bool loading = false;
  int pages = 0;
  int currentPage = 1;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color(int.parse('0xFF0C0A33')),
        title: const Text("Peliculas", style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold))
      ),
      body: getBody(),
    );
  }

  Container getBody() => Container(
    padding: const EdgeInsets.all(10),
    decoration: BoxDecoration(
      color: Color(int.parse('0xFF150D48')),
    ),
    child: Column(
      children: [
        SearchBar(
          controller: _nameController,
          hintText: "Buscar películas",
          textStyle: MaterialStateTextStyle.resolveWith((states) => const TextStyle(color: Colors.white)),
          hintStyle: MaterialStateTextStyle.resolveWith((states) => const TextStyle(color: Colors.white)),
          backgroundColor: MaterialStateColor.resolveWith((states) => const Color.fromARGB(80, 255, 255, 255)),
          trailing: <Widget>[
            IconButton(
              icon: const Icon(Icons.search),
              color: Colors.white,
              onPressed: () => {
                setState(() => loading = true),
                searchMovies(1).then((value) {
                  if (value.statusCode == 200){
                    SearchModel search = searchModelFromJson(value.body);
                    setState(() {
                      results = search.results;
                      pages = search.totalPages;
                      loading = false;
                      currentPage = 1;
                    });
                  } else {
                    setState(() {
                      results = [];
                      pages = 0;
                      loading = false;
                      //create a toast
                      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("No hay resultados")));
                    });
                  }
                  })
              },
            )
          ],
          onSubmitted: (value) => {
            searchMovies(1).then((value) {
                  SearchModel search = searchModelFromJson(value.body);
                  setState(() {
                    results = search.results;
                    pages = search.totalPages;
                  });
                  })
          },
        ),
        const SizedBox(height: 10),
        Expanded(
          child: getMovieList(),
        ),
        const SizedBox(height: 10),
        getPageSelector()
      ]),
  );

  Future<http.Response> searchMovies(int page){
    var queryParams = {
      'query': _nameController.text,
      'page': page.toString(),
      "language": "es"
    };
    Uri uri = Uri.parse("https://api.themoviedb.org/3/search/movie").replace(queryParameters: queryParams);
    String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YWQxOTJlYWNiOWU2ZGMxOTZjYjdmM2E1NzAwZDk0NCIsInN1YiI6IjY1MjM0MzBmYjNmNmY1MDBjNWZmMzM4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q7p9fFs7UCnpTikch6Pc8kfwUz1x3gwqUXLnKdlYEio";
    return http.get(uri, headers: {"Accept": "application/json", "Authorization": apiKey});  
  } 

  Widget getMovieList(){
    if (loading){
      return const Center(child: CircularProgressIndicator(color: Colors.white,));
    }
    return ListView.builder(
        physics: const BouncingScrollPhysics(),
        itemCount: results.length,
        itemBuilder: (BuildContext context, int index) {
          Result result = results[index];
          return InkWell(
            onTap: () => {
              Navigator.pushNamed(context, "/movie", arguments: {"id": result.id})
            },
            child: Card(
              clipBehavior: Clip.antiAlias,
              color: Color(int.parse('0xFF0C0A33')),
              child: Row(
                children: [
                  getMovieImage(result.posterPath),
                  const SizedBox(width: 10),
                  Expanded(
                    child:Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          result.title, 
                          style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white)
                        ),
                        Text(
                          result.releaseDate!=null? DateFormat("dd/MM/yyyy").format(result.releaseDate!):'',
                          style: const TextStyle(fontSize: 14, color: Colors.white)
                        ),
                        const SizedBox(height: 5),
                        Text(
                          result.overview, 
                          overflow: TextOverflow.ellipsis, 
                          maxLines: 2,
                          style: const TextStyle(color: Colors.white),)
                      ],
                    )
                  )
                  
                ],
              ),
            ),
          );
        },
      );
  }

  Widget getPageSelector(){
    if (pages < 2){
      return const SizedBox.shrink();
    }
    return SizedBox(
      height: 30,
      child: ListView.builder(
      itemCount: pages,
      scrollDirection: Axis.horizontal,
      itemBuilder: (BuildContext context, int index) {
        return InkWell(
          onTap: () => {
            searchMovies(index+1).then((value) {
                  SearchModel search = searchModelFromJson(value.body);
                  setState(() {
                    currentPage = index+1;
                    results = search.results;
                    pages = search.totalPages;
                  });
                  })
          }, 
          child: ClipOval(
            child: Container(
              width: 30,
              height: 30,
              color: index+1==currentPage?Color(int.parse("0xFF7156E5")):Colors.transparent,
              child: Center(
                child: Text(
                  (index+1).toString(), 
                  style: TextStyle(color: index+1!=currentPage?Color(int.parse("0xFF7156E5")):Colors.white,
              )),
            ),
            ) 
          )
        );
      },
    ),
    );
  }

  getMovieImage(String? path){
    if (path == null || path == ""){
      return Image.asset("images/no_img_icon.png", width: 100);
    }
    return Image.network("https://image.tmdb.org/t/p/w500$path", width: 100);
  }
}