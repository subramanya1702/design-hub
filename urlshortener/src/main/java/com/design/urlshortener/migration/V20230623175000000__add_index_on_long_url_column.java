package com.design.urlshortener.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

@ChangeLog(order = "001")
public class V20230623175000000__add_index_on_long_url_column {

    @ChangeSet(order = "001", id = "addIndexOnLongUrl", author = "Subramanya K")
    public void addIndexOnLongUrl(MongoDatabase database) {
        IndexOptions indexOptions = new IndexOptions().unique(true);
        database.getCollection("shortUrl").createIndex(Indexes.ascending("userId", "longUrl"), indexOptions);
    }
}
