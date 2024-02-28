import 'package:flutter/material.dart';

class CardPage extends StatelessWidget {
  const CardPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text("Card"),
        ),
        body: Container(
          padding: const EdgeInsets.all(10),
          child: ListView(children: <Widget>[
            getCard(),
            getCard(),
            getCard(),
            Container(
              decoration: BoxDecoration(
                  border: Border.all(color: Colors.black, width: 2)),
              margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 20),
              child: Image.asset("assets/test.jpg"),
            ),
            const CircleAvatar(
              backgroundImage: AssetImage("assets/test.jpg"),
              radius: 100,
            )
          ]),
        ));
  }

  Card getCard() {
    return Card(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          const ListTile(
            leading: CircleAvatar(
              backgroundImage: NetworkImage(
                  'https://flutter.github.io/assets-for-api-docs/assets/widgets/owl-2.jpg'),
              radius: 100,
            ),
            title: Text('The Enchanted Nightingale'),
            subtitle: Text('Music by Julie Gable. Lyrics by Sidney Stein.'),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              TextButton(
                child: const Text('BUY TICKETS'),
                onPressed: () {/* ... */},
              ),
              const SizedBox(width: 8),
              TextButton(
                child: const Text('LISTEN'),
                onPressed: () {/* ... */},
              ),
              const SizedBox(width: 8),
            ],
          ),
        ],
      ),
    );
  }
}
