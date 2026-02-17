import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

class PushNotificationService {
  static FirebaseMessaging messaging = FirebaseMessaging.instance;
  static final FlutterLocalNotificationsPlugin _localNotifications = FlutterLocalNotificationsPlugin();


  //Configuración del canal de notificacioens de Android
  static const AndroidNotificationChannel _channel = AndroidNotificationChannel(
    'dam', // id
    'Notificaciones', // nombre del canal
    description: 'Este canal se usa para notificaciones críticas.',
    importance: Importance.max,
  );


  //Pedir permisos
  static Future<void> inicializarApp() async {
    // Solicitar permisos
    NotificationSettings settings = await messaging.requestPermission(
      alert: true,
      badge: true,
      sound: true,
    );
    print('Estado de permisos: ${settings.authorizationStatus}');


    const AndroidInitializationSettings initializationSettingsAndroid =
    AndroidInitializationSettings('@mipmap/ic_launcher');


    const InitializationSettings initializationSettings = InitializationSettings(
      android: initializationSettingsAndroid,
    );


    await _localNotifications.initialize(


      onDidReceiveNotificationResponse: (NotificationResponse details) {
        print("Notificación pulsada: ${details.payload}");
      }, settings: initializationSettings,
    );
    await _localNotifications
        .resolvePlatformSpecificImplementation<AndroidFlutterLocalNotificationsPlugin>()
        ?.createNotificationChannel(_channel);


    String? token = await messaging.getToken();
    print("FCM Token: $token");


    await messaging.subscribeToTopic('dam');
    print("Suscrito al topic: dam");


    //mostrar la notificación
    FirebaseMessaging.onMessage.listen((RemoteMessage message) {
      RemoteNotification? notification = message.notification;
      AndroidNotification? android = message.notification?.android;


      if (notification != null && android != null) {
        _localNotifications.show(
            notificationDetails: NotificationDetails(
              android: AndroidNotificationDetails(
                _channel.id,
                _channel.name,
                channelDescription: _channel.description,
                icon: android.smallIcon,
                importance: Importance.max,
                priority: Priority.high,
              ),
            ),
            payload: message.data.toString(),
            id: notification.hashCode,
            title: notification.title,
            body: notification.body
        );
      }
    });
  }
}
