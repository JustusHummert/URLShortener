package com.urlShortener.repositories;

import com.urlShortener.entities.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, String> {
}
