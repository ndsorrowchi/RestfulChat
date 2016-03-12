This is a social site demo.

The following features are restful apis:
1. get user information by id
2. get user’s friends list by id
3. search user by id or nickname
4. register (but not login, no beans)
5. add friend (actually should be called “follow”)

The following features are servlets:
1. login
2. register with login
3. logout
4. chatting (post message, and update message list)
5. check if a email address has been registered

———————————————————————————————————————————————————
Basic logic:
1. Register/Login;
2. Search for user by nickname or id(email);
3. Add that user as friend so he will appear in our friends list
   (friends list refreshes every 10 seconds, online and offline friends
    have different displays).
4. The other user does the same to add you as friend.
5. Click on a friend in friends list, the information will show
   up on the right of page, along with a ’start chat’ button
6. Click on the button to show the chat ui.
7. Chat and the other user will see immediately (refreshes every 3 seconds).

Notice:
1. Both users have to add the other as friend, in order to see the message.
   Otherwise, only the one who added the other as friend can see what he sent.
2. If you are chatting with one friend, and starts chat with the other friend,
   the current chat will be stopped. Instead of it, the new chat will be there,
   and the webpage will start listening for the new chat.
3. A robot is the default friend for any user. You can chat with him, but he can
   only send back:’Robot auto reply’. But he is always marked as online.
4. Nickname is not unique, e.g., ‘a@gmail.com’ and ‘b@gmail.com’ both uses ‘user’
   as nickname.

Environment:
1. derby db is placed in dbms folder. (username: mic82, password: eb3).
2. please make sure the libraries required exists (jersey 2.5.1, JAX-RS 2.0,
   org.json, and derby drivers).
3. When testing, unless chatting with robot, please use two different browsers
   for two different users, so that you can see the changes immediately.
4. If derby db does not work, run everything in sql.txt with a new db.

———————————————————————————————————————————————————
Java Packages & Classes:

Services (restful api)
-ApplicationConfig: base of restful container
-RestUserService: restful apis

ViewModels (used for storing data from client side)
-AddFriendViewModel
-MessageViewModel
-MessageListViewModel
-UserViewModel
-UserListViewModel
-UserRegisterViewModel

Utils
-CommonUtils (help produce error jsons)
-ConvertModels (convert between ViewModels and Models, meanwhile checks nulls)
-MyContextListener (has OnInit() and OnDestroy, help do sth with sql.Connection)
-DBSingleton (Singleton, only has one instance, maintains db connection)

Models
-AddFriendModel
-MessageModel
-MessageListModel
-UserModel
-UserListModel
-UserRegisterModel

DataAccess
-MyUserService (All user oriented function implementations)
-MyChatService (All chat oriented function implementations)

beans
-userbean

Servlets
-Chat
-Check
-home
-Login
-Logout
-Register
