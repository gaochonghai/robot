package com.movie.springboot.mapper;

import com.movie.springboot.bean.Result;
import org.springframework.data.repository.CrudRepository;

/**
 * implement the jpa CRUD to insert record into database
 * Result --- including input and output
 * @return
 */
public interface ResultRepository extends CrudRepository<Result, Long> {
}
