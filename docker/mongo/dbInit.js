
use memex;
now = new ISODate();

db.userDetails.insertOne({
    "_id" : "default",
    "password" : "${MONGO_DEFAULT_USER_PW}",
    "authorities" : [],
    "createDateTime" : now,
    "updateDateTime" : now,
    "_class" : "com.matthewjohnson42.personalMemexService.data.mongo.entity.UserDetailsMongo"
});
