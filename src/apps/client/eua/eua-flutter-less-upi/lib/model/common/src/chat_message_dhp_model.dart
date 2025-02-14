import 'package:uhi_flutter_app/model/common/common.dart';

class ChatMessageDhpModel {
  ContextModel? context;
  ChatMessage? message;

  ChatMessageDhpModel({this.context, this.message});

  ChatMessageDhpModel.fromJson(Map<String, dynamic> json) {
    context = json['context'] != null
        ? new ContextModel.fromJson(json['context'])
        : null;
    message = json['message'] != null
        ? new ChatMessage.fromJson(json['message'])
        : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.context != null) {
      data['context'] = this.context!.toJson();
    }
    if (this.message != null) {
      data['message'] = this.message!.toJson();
    }
    return data;
  }
}

class ChatMessage {
  ChatIntent? intent;

  ChatMessage({this.intent});

  ChatMessage.fromJson(Map<String, dynamic> json) {
    intent =
        json['intent'] != null ? new ChatIntent.fromJson(json['intent']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.intent != null) {
      data['intent'] = this.intent!.toJson();
    }
    return data;
  }
}

class ChatIntent {
  ChatMsg? chat;

  ChatIntent({this.chat});

  ChatIntent.fromJson(Map<String, dynamic> json) {
    chat = json['chat'] != null ? new ChatMsg.fromJson(json['chat']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.chat != null) {
      data['chat'] = this.chat!.toJson();
    }
    return data;
  }
}

class ChatMsg {
  Sender? sender;
  Sender? receiver;
  Content? content;
  ChatTime? time;

  ChatMsg({this.sender, this.receiver, this.content, this.time});

  ChatMsg.fromJson(Map<String, dynamic> json) {
    sender =
        json['sender'] != null ? new Sender.fromJson(json['sender']) : null;
    receiver =
        json['receiver'] != null ? new Sender.fromJson(json['receiver']) : null;
    content =
        json['content'] != null ? new Content.fromJson(json['content']) : null;
    time = json['time'] != null ? new ChatTime.fromJson(json['time']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.sender != null) {
      data['sender'] = this.sender!.toJson();
    }
    if (this.receiver != null) {
      data['receiver'] = this.receiver!.toJson();
    }
    if (this.content != null) {
      data['content'] = this.content!.toJson();
    }
    if (this.time != null) {
      data['time'] = this.time!.toJson();
    }
    return data;
  }
}

class Sender {
  Person? person;

  Sender({this.person});

  Sender.fromJson(Map<String, dynamic> json) {
    person =
        json['person'] != null ? new Person.fromJson(json['person']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.person != null) {
      data['person'] = this.person!.toJson();
    }
    return data;
  }
}

class Person {
  String? name;
  String? image;
  String? cred;
  String? gender;

  Person({this.name, this.image, this.cred, this.gender});

  Person.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    image = json['image'];
    cred = json['cred'];
    gender = json['gender'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['image'] = this.image;
    data['cred'] = this.cred;
    data['gender'] = this.gender;
    return data;
  }
}

class Content {
  String? contentId;
  String? contentValue;
  String? contentType;
  String? contentUrl;
  String? contentFilename;
  Content(
      {this.contentId,
      this.contentValue,
      this.contentType,
      this.contentFilename});
  Content.fromJson(Map<String, dynamic> json) {
    contentId = json['content_id'];
    contentValue = json['content_value'];
    contentType = json['content_type'];
    contentUrl = json['content_url'];
    contentFilename = json['content_fileName'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['content_id'] = contentId;
    data['content_value'] = contentValue;
    data['content_type'] = contentType;
    data['content_url'] = contentUrl;
    data['content_fileName'] = contentFilename;
    return data;
  }
}

class ChatTime {
  String? timestamp;

  ChatTime({this.timestamp});

  ChatTime.fromJson(Map<String, dynamic> json) {
    timestamp = json['timestamp'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['timestamp'] = this.timestamp;
    return data;
  }
}
