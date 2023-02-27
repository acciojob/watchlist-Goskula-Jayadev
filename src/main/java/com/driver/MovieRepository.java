package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> DBmovie;
    private HashMap<String,Director> DBdirector;

    private HashMap<String, List<String>> DBDirMapMovie;

    public MovieRepository(HashMap<String, Movie> DBmovie, HashMap<String, Director> DBdirector, HashMap<String, List<String>> DBDirMapMovie) {
        this.DBmovie = DBmovie;
        this.DBdirector = DBdirector;
        this.DBDirMapMovie = DBDirMapMovie;
    }

    public String  addMovie(Movie movie){
        String name = movie.getName();
        DBmovie.put(name,movie);
        return "success";
    }

    public String addDirector(Director director){
        String name = director.getName();
        DBdirector.put(name,director);
        return "success";
    }

    public String addMovieDirectorPair(String movname,String dirname){

        if(DBmovie.containsKey(movname) && DBdirector.containsKey(dirname)){
            //System.out.println("in addMovieDirectorPair");
            if(DBDirMapMovie.containsKey(dirname))
            {
                DBDirMapMovie.get(dirname).add(movname);
            }

            else
            {
                List<String> l = new LinkedList<>();
                l.add(movname);
                DBDirMapMovie.put(dirname,l);
            }

            return "success";
        }

        return null;
    }


    public Movie getMovieByName(String name){
        if(!DBmovie.containsKey(name)){
            return null;
        }
        return DBmovie.get(name);
    }

    public Director getDirectorByName(String name){
        if(!DBdirector.containsKey(name)){return null;}
        return DBdirector.get(name);
    }


    public List<String> getMoviesByDirectorName(String name){

        if(!DBDirMapMovie.containsKey(name)) {
            System.out.println("BYE");
            return null;
        }
        return DBDirMapMovie.get(name);

    }

    public List<String> findAllMovies(){
        List<String> list = new LinkedList<>();
        for(String s:DBmovie.keySet()){
            list.add(DBmovie.get(s).getName());
        }
        return list;
    }

    public String deleteDirectorByName(String name){
        if(!DBDirMapMovie.containsKey(name))
            return null;
        List<String> l = DBDirMapMovie.get(name);
        if(l!=null) {
            for (String s1 : l) {
                if (DBmovie.containsKey(s1)) {
                    DBmovie.remove(s1);
                }
            }
            DBDirMapMovie.remove(name);
            DBdirector.remove(name);
        }
        return "success";
    }

    public String deleteAllDirectors(){
        if(DBDirMapMovie.isEmpty()){return null;}
        for(String s:DBDirMapMovie.keySet()){
            List<String> l = DBDirMapMovie.get(s);
            if(l!=null) {
                for (String s1 : l) {
                    if (DBmovie.containsKey(s1)) {
                        DBmovie.remove(s1);
                    }
                }
                DBdirector.clear();
            }
        }
        return "success";
    }
}
