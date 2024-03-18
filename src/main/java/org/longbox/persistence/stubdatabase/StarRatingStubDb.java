package org.longbox.persistence.stubdatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.StarRatingDao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class StarRatingStubDb implements StarRatingDao, JsonConvertor{
	 private List<StarRatingDto> starRatingsStubData = new ArrayList<>();
	    private final String ABSOLUTE_FILE_PATH = "src/main/resources/StarRatingStubDb.json";

	    @Override
	    public StarRating getStarRatingById(long userId, long comicId) {
	        return null;
	    }

	    @Override
	    public List<StarRatingDto> getStarRatingsByComic(Long comicID) {
	        List<StarRatingDto> StarRatingList = new ArrayList<>();
	        for(StarRatingDto c: starRatingsStubData){
	            if(c.getComicBookId() == comicID){
	                StarRatingList.add(c);
	            }
	        }
	        return StarRatingList;
	    }

	    @Override
	    public void saveStarRating(StarRatingDto StarRatingDTO) {
	        starRatingsStubData.add(StarRatingDTO);
	    }

	    @Override
	    public List<StarRatingDto> getStarRatingsByUser(Long userID) {
	        List<StarRatingDto> StarRatingList = new ArrayList<>();
	        for(StarRatingDto c: starRatingsStubData){
	            if(c.getUserId() == userID){
	                StarRatingList.add(c);
	            }
	        }
	        return StarRatingList;
	    }

	    public void loadJsonToArrayList(){
	        starRatingsStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
	    }

	    @Override
	    public void serializeStubData() {
	        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
	        String json = gson.toJson(starRatingsStubData);
	        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
	            out.print(json);
	        } catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    public List<StarRatingDto> deserializeStubData(String filepath) {
	        Type listType = new TypeToken<ArrayList<StarRatingDto>>(){}.getType();
	        JsonReader reader = null;

	        try {
	            reader = new JsonReader(new FileReader(filepath));
	        }
	        catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
	        return gson.fromJson(reader, listType);
	    }
}
