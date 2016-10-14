package home.skwmium.skilltest.model;

import java.util.List;

import home.skwmium.skilltest.model.dto.Character;
import home.skwmium.skilltest.model.dto.House;
import rx.Observable;

public interface Model {
    Observable preloadData();

    Observable<List<House>> getHouses();

    Observable<List<Character>> getCharactersByHouse(String houseLink);

    Observable<Character> getCharacter(String characterLink);
}
