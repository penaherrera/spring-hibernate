package sv.edu.udb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.domain.Post;
import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // Inyección de dependencias a través del constructor
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Lógica del servicio
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        System.out.println("Posts: " + posts); // Imprimir la lista de posts
        return posts;
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
