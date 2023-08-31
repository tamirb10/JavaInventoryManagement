package com.hit.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.hit.dm.Product;

public interface Dao <ID extends Serializable ,T>{
	
	T get (String id) throws JsonIOException, JsonSyntaxException, FileNotFoundException;
	
	List<T> getAll () throws JsonIOException, JsonSyntaxException, FileNotFoundException;
	
	void delete(String t) throws JsonIOException, JsonSyntaxException, IOException;
	
	void save(T t) throws JsonIOException, JsonSyntaxException, IOException;
	
    void deleteAll() throws JsonIOException, JsonSyntaxException, IOException;
}
