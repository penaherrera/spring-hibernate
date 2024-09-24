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
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Endpoint para obtener un post por ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // Endpoint para crear un nuevo post
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Post created successfully");
    }

    // Endpoint para eliminar un post por ID
    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
