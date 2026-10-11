package com.uselink.api.repositories;

import com.uselink.api.domain.link.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {}
