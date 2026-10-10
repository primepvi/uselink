package com.uselink.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uselink.api.domain.link.CreateLinkRequestDTO;
import com.uselink.api.domain.link.Link;
import com.uselink.api.repositories.LinkRepository;

@RestController
@RequestMapping("/links")
public class LinkController {
	private final LinkRepository linkRepository;
	
	public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}
	
	@PostMapping
	public ResponseEntity<Link> create(@RequestBody CreateLinkRequestDTO dto) {
		Link rawLink = new Link();
		rawLink.setTargetUrl(dto.targetUrl());
		
		Link link = linkRepository.save(rawLink);
		return ResponseEntity.status(HttpStatus.CREATED).body(link);
	}
	
	@GetMapping
	public ResponseEntity<List<Link>> fetch() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(linkRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public Link findById(@PathVariable Long id) {
		return linkRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid link id has provided."));
	}
	
	@PatchMapping("/{id}/access")
	public Link registerAccess(@PathVariable Long id) {
		Link link = linkRepository.findById(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid link id has provided.")
		);
		
		link.registerAccess();
		return linkRepository.save(link);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		if (!linkRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid link id has provided.");
		}
		
		linkRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
