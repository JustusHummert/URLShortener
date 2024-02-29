package com.URLShortener.server.repositories;

import com.URLShortener.server.entities.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, String> {
}
