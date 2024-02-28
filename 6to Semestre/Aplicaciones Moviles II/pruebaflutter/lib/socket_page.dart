import 'package:flutter/material.dart';
import 'package:socket_io_client/socket_io_client.dart' as IO;

class SocketPage extends StatefulWidget {
  const SocketPage({super.key});

  @override
  State<SocketPage> createState() => _SocketPageState();
}

class _SocketPageState extends State<SocketPage> {
  List messages = [];
  TextEditingController messageController = TextEditingController();
  late var socket;

  @override
  void initState() {
    super.initState();
    manageSocket();
  }

  void sendMessage() {
    String message = messageController.text;
    if (message.isNotEmpty) {
      print('Sending message: $message');
      socket.emit('chat message', {'msg': message, 'user': 'flutter'});
      // Limpia el campo de texto después de enviar el mensaje
      messageController.clear();
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              title: const Text('Socket'),
            ),
            body: Column(
              children: [
                Expanded(
                  child: getMessageList(),
                ),
                getMessageForm(),
              ],
            )));
  }

  ListView getMessageList() {
    return ListView.separated(
      separatorBuilder: (BuildContext context, int index) => Divider(),
      itemCount: messages.length,
      itemBuilder: (BuildContext context, int index) {
        return getMessageListItem(index);
      },
    );
  }

  Padding getMessageForm() {
    return Padding(
      padding: EdgeInsets.all(16.0),
      child: Row(
        children: [
          Expanded(
            child: TextFormField(
              controller: messageController,
              decoration: InputDecoration(
                labelText: 'Message',
              ),
            ),
          ),
          SizedBox(width: 16.0),
          ElevatedButton(
            onPressed: sendMessage,
            child: Text('Send'),
          ),
        ],
      ),
    );
  }

  ListTile getMessageListItem(int index) {
    return ListTile(
      title: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(messages[index]['user'],
              style: TextStyle(fontWeight: FontWeight.bold)),
          Text(messages[index]['msg']),
        ],
      ),
    );
  }

  void manageSocket() {
    socket = IO.io('http://192.168.100.222:3000', <String, dynamic>{
      'autoConnect': false,
      'transports': ['websocket'],
    });
    socket.connect();
    socket.on('connect', (_) {
      print('connect');
    });
    socket.on(
        'chat message',
        (data) => {
              setState(() {
                messages.add(data);
              })
            });
  }

  @override
  void dispose() {
    super.dispose();
    socket.disconnect();
  }
}
