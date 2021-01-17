
use documents;
now = new ISODate();

db.userDetails.insertOne({
    "_id" : "default",
    "password" : "8e2c10d7807a3aa1e045f6072fd2bc00f976ba26b829368155bddf6aff5d746b8b0e5ac40082b63f",
    "authorities" : [],
    "createDateTime" : now,
    "updateDateTime" : now,
    "_class" : "com.matthewjohnson42.personalMemexService.data.mongo.entity.UserDetailsMongo"
});
