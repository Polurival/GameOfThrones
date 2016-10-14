package home.skwmium.skilltest.model.network;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import home.skwmium.skilltest.model.dto.RealmString;
import io.realm.RealmList;

public class RealmStringDeserialisator extends StdDeserializer<RealmList<RealmString>> {
    protected RealmStringDeserialisator(Class<?> vc) {
        super(vc);
    }

    protected RealmStringDeserialisator(JavaType valueType) {
        super(valueType);
    }

    protected RealmStringDeserialisator(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public RealmList<RealmString> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (!p.isExpectedStartArrayToken()) {
            throw new RuntimeJsonMappingException("Token does not start array.");
        }
        RealmList<RealmString> realmStrings = new RealmList<>();
        JsonToken token;
        do {
            token = p.nextToken();
            if (token == JsonToken.VALUE_STRING)
                realmStrings.add(new RealmString(p.getValueAsString()));
        } while (token != JsonToken.END_ARRAY);
        return realmStrings;
    }
}
