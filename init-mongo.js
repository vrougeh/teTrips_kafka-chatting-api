// // init-mongo.js
// // db.createUser({
// //     user: "root",
// //     pwd: "password",
// //     roles: [{ role: "root", db: "admin" }]
// // });
// db.auth("root","password")
// db.createUser({
//     user: "chatuser",
//     pwd: "chatpassword",
//     roles: [{ role: "readWrite", db: "chatdb" }]
// });
// db.auth("chatuser","chatpassword")
// db = db.getSiblingDB('chatdb');
// db.createCollection('chatMessages');
//
//       - ./init-mongo.js:/docker-entrypoint-initdb.d/init-mongo.js:ro