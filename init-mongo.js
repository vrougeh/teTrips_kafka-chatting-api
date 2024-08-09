// Create the root user
db.createUser({
    user: "root",
    pwd: "password",
    roles: [{ role: "root", db: "admin" }]
});

// Authenticate as root user
db.auth("root", "password");

// Create chatuser with readWrite role on chatdb
db.createUser({
    user: "chatuser",
    pwd: "chatpassword",
    roles: [{ role: "readWrite", db: "chatdb" }]
});

// Switch to chatdb
db = db.getSiblingDB('chatdb');

// Authenticate as chatuser (optional, usually not needed after user creation)
db.auth("chatuser", "chatpassword");

// Create chatMessages collection in chatdb
db.createCollection('chatMessages');