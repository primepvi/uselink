package com.uselink.api.services;

import org.hashids.Hashids;	
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {
	private final Hashids hashids;
	
	public LinkShortenerService(@Value("${SLUG_SALT}") String salt) {
		this.hashids = new Hashids(salt, 7);
	}
	
	public String slugFromId(Long id) { return this.hashids.encode(id); }
	public Long idFromSlug(String slug) { return this.hashids.decode(slug)[0]; }
}
