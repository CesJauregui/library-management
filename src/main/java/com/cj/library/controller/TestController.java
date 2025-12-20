package com.cj.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Endpoint accesible por cualquier usuario autenticado
     */
    @GetMapping("/user")
    public ResponseEntity<Map<String, String>> userAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> response = new HashMap<>();
        response.put("message", "User Content - Accessible by any authenticated user");
        response.put("username", auth.getName());
        response.put("authorities", auth.getAuthorities().toString());

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint solo para LIBRARIAN y ADMIN
     */
    @GetMapping("/librarian")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<Map<String, String>> librarianAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> response = new HashMap<>();
        response.put("message", "Librarian Content - Only LIBRARIAN and ADMIN can access");
        response.put("username", auth.getName());

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint solo para ADMIN
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> adminAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin Content - Only ADMIN can access");
        response.put("username", auth.getName());

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint público (sin autenticación)
     */
    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Public Content - No authentication required");

        return ResponseEntity.ok(response);
    }
}
