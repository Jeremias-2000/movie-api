package com.jeremias.pinheiro.movie.api.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.jeremias.pinheiro.movie.api.enums.MovieGenre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        //propriedades nao mapeadas
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //falha de alguma propriedade estiver vazia
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        //serve para compatibilidade de arrays
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        //Serialize datas
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(foodTypeModuleMapper());
        return objectMapper;
    }

    public SimpleModule foodTypeModuleMapper(){
        SimpleModule dateModule = new SimpleModule("JsonFoodTypeModule", PackageVersion.VERSION);
        dateModule.addSerializer(MovieGenre.class,new MovieGenreCerialize());
        dateModule.addDeserializer(MovieGenre.class,new MovieGenreDeserialize());
        return dateModule;
    }
    class MovieGenreCerialize extends StdSerializer<MovieGenre> {

        public MovieGenreCerialize(){
            super(MovieGenre.class);
        }

        @Override
        public void serialize(MovieGenre genre, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(genre.getDescription());
        }
    }
    class MovieGenreDeserialize extends StdDeserializer<MovieGenre> {


        protected MovieGenreDeserialize() {
            super(MovieGenre.class);
        }

        @Override
        public MovieGenre deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return MovieGenre.of(jsonParser.getText());
        }
    }
}
