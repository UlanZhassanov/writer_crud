package com.writercrud.writer_crud.controller;

import com.writercrud.writer_crud.model.Post;
import com.writercrud.writer_crud.repository.GsonPostRepositoryImpl;
import com.writercrud.writer_crud.repository.PostRepository;

import java.util.List;

public class PostController {
    PostRepository postRepository = new GsonPostRepositoryImpl("src/main/resources/data/posts.json");

    public Post savePost(Post post){
        Post savedPost = postRepository.save(post);
        return savedPost;
    }


    public List<Post> getAllPosts(){
        List<Post> posts = postRepository.getAll();
        return posts;
    }

    public Post getPostById(Integer id){
        Post post = postRepository.getById(id);
        return post;
    }

    public Post updatePost(Post post){
        Post updatedPost = postRepository.update(post);
        return updatedPost;
    }

    public void deletePost(Integer id){
        postRepository.deleteById(id);
    }

}
