package sv.edu.udb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // Inyección de dependencias a través del constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Endpoint para obtener todos los posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts); // 200 OK
    }

    // Endpoint para obtener un post por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // Endpoint para crear un nuevo post
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Post created successfully"); // 201 Created
    }

    // Endpoint para eliminar un post por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            postService.deletePost(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // Endpoint para actualizar un post
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post existingPost = postService.getPostById(id);
        if (existingPost != null) {
            postService.updatePost(id, updatedPost);
            return ResponseEntity.ok("Post updated successfully"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
